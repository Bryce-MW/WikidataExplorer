package model.data.source;

import com.google.gson.Gson;
import model.data.Qualifier;
import model.data.Reference;
import model.data.*;
import model.data.pages.Property;
import model.data.source.template.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class WebCollector extends Collector {
    /*
     * Class Description:
     * This class actually collects the JSON from the Wikidata website and converts it to the proper formats. It also
     *  handles some of the caching. It ended up doing a lot of the stuff which the Datum Query Service should have
     * done which is why I ended up making local collector extend this rather than having it just extend collector on
     *  its own.
     */
    private static final ArrayList<Entities> seen = new ArrayList<>(30);
    protected static Entities loaded;
    private final Gson gson;

    /*
     * REQUIRES: repository is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public WebCollector(LocalRepository repository) {
        super(repository);
        gson = new Gson();
    }

    /*
     * REQUIRES: ID is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    private static String formatURL(String id) {
        return "https://www.wikidata.org/wiki/Special:EntityData/" + id + ".json";
    }

    /*
     * REQUIRES: property is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getEntityName(String property) throws NotFoundException {
        Entity entity = getJson(property).entities.get(property);
        Map<String, Label> labels = entity.labels;
        if (labels == null) {
            labels = entity.representations;
        }
        Label name = labels.get("en");
        if (name == null) {
            Label other = new Label();
            other.value = "";
            return labels.values().stream().findAny().orElse(other).value;
        }
        return name.value;
    }

    /*
     * REQUIRES: tree is of size at least 3 and contains a valid tree of IDs, qualifierQuery is not null
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery) {
        ArrayList<Qualifier> result = new ArrayList<>(5);

        Map<String, List<model.data.source.template.Qualifier>> qualifiers;
        try {
            qualifiers = tryGetQualifiers(qualifierQuery, tree.get(0), tree.get(1), tree.get(2));
        } catch (NotFoundException e) {
            return result;
        }

        if (qualifiers == null) {
            return result;
        }

        for (String s : qualifiers.keySet()) {
            for (model.data.source.template.Qualifier subQualifier : qualifiers.get(s)) {
                try {
                    result.add(new Qualifier(new Property(s, qualifierQuery),
                            Value.parseData(subQualifier.datavalue, subQualifier.datatype, qualifierQuery),
                            qualifierQuery));
                } catch (NotFoundException ignored) {
                    // If a Value can't be created then just don't add it to the list
                }
            }
        }
        return result;
    }

    /*
     * REQUIRES: qualifierQuery is not null, id, claim, and item are a valid tree of Wikidata IDs
     * MODIFIES: none
     * EFFECTS :
     */
    private Map<String, List<model.data.source.template.Qualifier>> tryGetQualifiers(DatumQueryService qualifierQuery,
                                                                                     String id,
                                                                                     String claim,
                                                                                     String item)
            throws NotFoundException {
        Map<String, List<model.data.source.template.Qualifier>> qualifiers;
        qualifiers = getJson(id).entities.get(id)
                .claims.get(claim).stream()
                .filter((i) -> Value.parseData(i.mainsnak.datavalue, i.mainsnak.datatype, qualifierQuery)
                        .getID().equals(item))
                .findAny().orElseThrow(() -> new NotFoundException(id, claim, item))
                .qualifiers;
        return qualifiers;
    }

    /*
     * REQUIRES: tree is a valid length three tree of Wikidata IDs. refQueryService is not null
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public ArrayList<Reference> getReferences(List<String> tree, DatumQueryService refQueryService) {
        ArrayList<Reference> references = new ArrayList<>();
        List<Claim> claims;
        try {
            claims = getJson(tree.get(0))
                    .entities.get(tree.get(0))
                    .claims.get(tree.get(1));
        } catch (NotFoundException e) {
            return references;
        }

        for (Claim claim : claims) {
            Value parsed = Value.parseData(claim.mainsnak.datavalue, claim.mainsnak.datatype, refQueryService);
            if (parsed.getID().equals(tree.get(2)) && claim.references != null) {
                for (model.data.source.template.Reference reference : claim.references) {
                    Map<String, ArrayList<Reference>> snaks = new HashMap<>();
                    for (String s : reference.snaks.keySet()) {
                        ArrayList<Reference> values = iterateSnaks(refQueryService, reference, s);
                        snaks.put(s, values);
                    }
                    reference.snaksOrder.stream().map(snaks::get).forEach(references::addAll);
                }
            }
        }
        return references;
    }

    /*
     * REQUIRES: refQueryService is not null, reference is a valid Reference, s is in the keys of the Map
     * reference.snaks
     * MODIFIES: none
     * EFFECTS :
     */
    private ArrayList<Reference> iterateSnaks(DatumQueryService refQueryService,
                                              model.data.source.template.Reference reference, String s) {
        ArrayList<Reference> values = new ArrayList<>();
        for (Snak snak : reference.snaks.get(s)) {
            try {
                values.add(new Reference(new Property(snak.property, refQueryService),
                        Value.parseData(snak.datavalue, snak.datatype, refQueryService),
                        refQueryService));
            } catch (NotFoundException e) {
                // Just don't add the reference
            }
        }
        return values;
    }

    /*
     * REQUIRES: id is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getEntityDescription(String id) throws NotFoundException {
        Entity entity = getJson(id).entities.get(id);
        Map<String, Description> descriptions = entity.descriptions;
        if (descriptions == null) {
            return "";
        }

        Description name = descriptions.get("en");
        if (name == null) {
            Description other = new Description();
            other.value = "";
            return descriptions.values().stream().findAny().orElse(other).value;
        }
        return name.value;
    }

    /*
     * REQUIRES: id is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public ArrayList<String> getStatementList(String id) throws NotFoundException {
        Map<String, List<Claim>> statements = getJson(id).entities.get(id).claims;
        Set<String> keys = statements.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        keyList.sort(Comparator.comparingInt((i) -> Integer.parseInt(i.substring(1))));
        return keyList;
    }

    /*
     * REQUIRES: tree is a valid length 2 Wikidata tree starting at item, statementService is not null
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService)
            throws NotFoundException {
        return new Statement(item, tree.get(1), statementService);
    }

    /*
     * REQUIRES: tree is a valid length 2 Wikidata tree, about is a valid Statement which this tree is about,
     * queryService is not null
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree,
                                                   Statement about, DatumQueryService queryService) {
        ArrayList<Value> result = new ArrayList<>(10);
        String id = tree.get(0);
        String property = tree.get(1);
        try {
            List<Claim> datumLinkList = getJson(id).entities.get(id).claims.get(property);
            for (Claim claim : datumLinkList) {
                result.add(new DatumLink(queryService, about, Value.parseData(claim.mainsnak.datavalue,
                        claim.mainsnak.datatype, queryService)));
            }
        } catch (NotFoundException ignored) {
            // Couldn't get data that I should've. Just return an empty list instead
        }
        return result;
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS :
     */
    @Override
    public Boolean triggerSave() {
        Entities entities = new Entities();
        entities.entities = new HashMap<>();
        for (Entities entitiesCollection : seen) {
            for (String entityKey : entitiesCollection.entities.keySet()) {
                entities.entities.put(entityKey, entitiesCollection.entities.get(entityKey));
            }
        }
        return repository.save(entities, gson);
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS :
     */
    @Override
    public Boolean triggerLoad() {
        loaded = repository.load(gson);
        return true;
    }

    /*
     * REQUIRES: urlString is a valid Wikidata ID
     * MODIFIES: this
     * EFFECTS :
     */
    protected Entities getJson(String urlStr) throws NotFoundException {
        for (Entities entities : seen) {
            if (entities.entities.containsKey(urlStr)) {
                return entities;
            }
        }
        if (loaded != null && loaded.entities.containsKey(urlStr)) {
            return loaded;
        }

        System.out.println(urlStr); // TODO: Remove debug statement, move it to a new class or something.

        try {
            URL url = new URL(formatURL(urlStr));
            URLConnection connection = url.openConnection();

            Entities newResult = gson.fromJson(new InputStreamReader(connection.getInputStream()), Entities.class);
            seen.add(newResult);
            return newResult;
        } catch (IOException e) {
            throw new NotFoundException(urlStr);
        }
    }
}
