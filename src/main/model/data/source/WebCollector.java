package model.data.source;

import com.google.gson.Gson;
import model.data.*;
import model.data.pages.Property;
import model.data.source.template.Claim;
import model.data.source.template.Entities;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@SuppressWarnings("ConstantConditions")
public class WebCollector extends Collector {
    private final Gson gson;
    private static final ArrayList<Entities> seen = new ArrayList<>(30);
    protected static Entities loaded;

    public WebCollector(LocalRepository repository) {
        super(repository);
        gson = new Gson();
    }

    @Override
    public String getEntityName(String property) throws NotFoundException {
        return getJson(property).entities.get(property).labels.get("en").value;
    }

    @Override
    public ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery) {
        ArrayList<Qualifier> result = new ArrayList<>(5);
        String id = tree.get(0);
        String claim = tree.get(1);
        String item = tree.get(2);

        Map<String, List<model.data.source.template.Qualifier>> qualifiers;
        try {
            qualifiers = getJson(id).entities.get(id)
                    .claims.get(claim).stream()
                    .filter((i) -> Value.parseData(i.mainsnak.datavalue.value, i.mainsnak.datatype, qualifierQuery)
                            .getID().equals(item))
                    .findAny().orElseThrow(() -> new NotFoundException(id, claim, item))
                    .qualifiers;
        } catch (NotFoundException e) {
            return result;
        }

        if (qualifiers == null) {
            return result;
        }

        for (String s : qualifiers.keySet()) {
            List<model.data.source.template.Qualifier> subQualis = qualifiers.get(s);
            for (model.data.source.template.Qualifier subQuali : subQualis) {
                try {
                    result.add(new Qualifier(new Property(s, qualifierQuery),
                            Value.parseData(subQuali.datavalue.value, subQuali.datatype, qualifierQuery),
                            qualifierQuery));
                } catch (NotFoundException ignored) {
                    // If a Value can't be created then just don't add it to the list
                }
            }
        }
        return result;
    }

    @Override
    public ArrayList<Reference> getReferences(List<String> tree) {
        return new ArrayList<>(0); // TODO: Implement later, not needed now
    }

    @Override
    public String getEntityDescription(String id) throws NotFoundException {
        return getJson(id).entities.get(id).descriptions.get("en").value;
    }

    @Override
    public ArrayList<String> getStatementList(String id) throws NotFoundException {
        Map<String, List<Claim>> statements = getJson(id).entities.get(id).claims;
        Set<String> keys = statements.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        keyList.sort(Comparator.comparingInt((i) -> Integer.parseInt(i.substring(1))));
        return keyList;
    }

    @Override
    public Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService) throws NotFoundException {
        return new Statement(item, tree.get(1), statementService);
    }

    @Override
    public ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree,
                                                   Statement about, DatumQueryService queryService) {
        ArrayList<Value> result = new ArrayList<>(10);
        String id = tree.get(0);
        String property = tree.get(1);
        try {
            List<Claim> datumLinkList = getJson(id).entities.get(id).claims.get(property);
            for (Claim claim : datumLinkList) {
                result.add(new DatumLink(queryService, about, Value.parseData(claim.mainsnak.datavalue.value,
                        claim.mainsnak.datatype, queryService)));
            }
        } catch (NotFoundException ignored) {
            // Couldn't get data that I should've. Just return an empty list instead
        }
        return result;
    }

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

    private static String formatURL(String id) {
        return "https://www.wikidata.org/wiki/Special:EntityData/" + id + ".json";
    }

    @Override
    public Boolean triggerLoad() {
        loaded = repository.load(gson);
        return true;
    }

    protected Entities getJson(String urlStr) throws NotFoundException {
        for (Entities entities : seen) {
            if (entities.entities.containsKey(urlStr)) {
                return entities;
            }
        }
        if (loaded != null && loaded.entities.containsKey(urlStr)) {
            return loaded;
        }
        System.out.println(urlStr); // TODO: Remove debug statement

        URLConnection connection = null;
        try {
            URL url = new URL(formatURL(urlStr));
            connection = url.openConnection();
        } catch (IOException ignored) {
            throw new NotFoundException(urlStr);
        }

        Entities newResult = new Entities();
        try {
            newResult = gson.fromJson(new InputStreamReader(connection.getInputStream()), Entities.class);
        } catch (IOException e) {
            throw new NotFoundException(urlStr);
        }

        seen.add(newResult);
        return newResult;
    }
}
