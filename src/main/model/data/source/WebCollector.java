package model.data.source;

import com.google.gson.Gson;
import model.data.*;
import model.data.pages.Item;
import model.data.pages.Property;
import model.data.source.template.Claim;
import model.data.source.template.Entities;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@SuppressWarnings("ConstantConditions")
public class WebCollector implements Collector {
    private final Gson gson;
    private static final ArrayList<Entities> seen = new ArrayList<>(30);

    public WebCollector() {
        gson = new Gson();
    }

    @Override
    public String getEntityName(String property) {
        return getJson(property).entities.get(property).labels.get("en").value;
    }

    @Override
    public ArrayList<Qualifier> getQualifiers(List<String> tree, DatumQueryService qualifierQuery) {
        ArrayList<Qualifier> result = new ArrayList<>(5);
        String id = tree.get(0);
        String claim = tree.get(1);
        String item = tree.get(2);
        Map<String, List<model.data.source.template.Qualifier>> qualifiers = getJson(id).entities.get(id)
                .claims.get(claim).stream()
                .filter((i) -> Value.parseData(i.mainsnak.datavalue.value, i.mainsnak.datatype, qualifierQuery)
                        .getID().equals(item))
                .findAny().orElseThrow(Error::new)
                .qualifiers;
        if (qualifiers == null) {
            return result;
        }
        for (String s : qualifiers.keySet()) {
            List<model.data.source.template.Qualifier> subQualis = qualifiers.get(s);
            for (model.data.source.template.Qualifier subQuali : subQualis) {
                result.add(new Qualifier(new Property(s, qualifierQuery),
                        Value.parseData(subQuali.datavalue.value, subQuali.datatype, qualifierQuery), qualifierQuery));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Reference> getReferences(List<String> tree) {
        return new ArrayList<>(0); // TODO: Implement later, not needed now
    }

    @Override
    public String getEntityDescription(String id) {
        return getJson(id).entities.get(id).descriptions.get("en").value;
    }

    @Override
    public ArrayList<String> getStatementList(String id) {
        Map<String, List<Claim>> statements = getJson(id).entities.get(id).claims;
        Set<String> keys = statements.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        keyList.sort(Comparator.comparingInt((i) -> Integer.parseInt(i.substring(1))));
        return keyList;
    }

    @Override
    public Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService) {
        return new Statement(item, tree.get(1), statementService);
    }

    @Override
    public ArrayList<Value> getDatumLinkListByTree(ArrayList<String> tree,
                                                   Statement about, DatumQueryService queryService) {
        ArrayList<Value> result = new ArrayList<>(10);
        String id = tree.get(0);
        String property = tree.get(1);
        List<Claim> datumLinkList = getJson(id).entities.get(id).claims.get(property);
        try {
            for (Claim claim : datumLinkList) {
                Map<String, String> value = (Map<String, String>) claim.mainsnak.datavalue.value;
                result.add(new DatumLink(queryService, about, new Item(value.get("id"), queryService)));
            }
        } catch (ClassCastException ignored) {
            return result;
        }
        return result;
    }

    private String formatURL(String id) {
        return "https://www.wikidata.org/wiki/Special:EntityData/" + id + ".json";
    }

    private Entities getJson(String urlStr) {
        for (Entities entities : seen) {
            if (entities.entities.containsKey(urlStr)) {
                return entities;
            }
        }
        System.out.println(urlStr); // TODO: Remove debug statement

        URLConnection connection = null;
        try {
            URL url = new URL(formatURL(urlStr));
            connection = url.openConnection();
        } catch (IOException ignored) {
            //ignored
        }

        Entities newResult = new Entities();
        try {
            newResult = gson.fromJson(new InputStreamReader(connection.getInputStream()), Entities.class);
        } catch (IOException e) {
            //ignored
        }

        seen.add(newResult);
        return newResult;
    }
}
