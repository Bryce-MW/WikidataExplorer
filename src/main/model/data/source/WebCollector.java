package model.data.source;

import model.data.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

@SuppressWarnings("ConstantConditions")
public class WebCollector implements Collector {
    private final ScriptEngine engine;

    public WebCollector() {
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("javascript");
    }

    @Override
    public String getEntityName(String property) {
        Map<String, Object> entities = (Map<String, Object>) getJson(property).get("entities"); // If I start getting
        // exceptions then so be it!
        Map<String, Object> entity = (Map<String, Object>) entities.get(property);
        Map<String, Object> labels = (Map<String, Object>) entity.get("labels");
        Map<String, String> label = (Map<String, String>) labels.get("en"); // TODO: Languages!
        return label.get("value");
    }

    @Override
    public ArrayList<Qualifier> getQualifiers(List<String> tree) {
        Map<String, Object> finalObject = (Map<String, Object>) getJson(tree.get(0)).get("entities"); // If I
        // start
        // getting
        // exceptions then so be it!
        finalObject = (Map<String, Object>) finalObject.get(tree.get(0));
        finalObject = (Map<String, Object>) finalObject.get("claims");
        List<Map<String, Object>> statements = (List<Map<String, Object>>) finalObject.get(tree.get(1));
        Map<String, Object> result = null;

        String finalName = tree.get(2);

        result = getStatement(statements, result, finalName);

        if (result == null) {
            //Something bad happened. Will probably crash
            return new ArrayList<>();
        }

        if (!result.containsKey("qualifiers")) {
            // no qualifiers
            return new ArrayList<>();
        }

        return new ArrayList<>(10); // TODO: Implement later, not needed now
    }

    private Map<String, Object> getStatement(List<Map<String, Object>> statements,
                                             Map<String, Object> result,
                                             String finalName) {
        for (Map<String, Object> statement : statements) {
            Map<String, Object> mainSnak = (Map<String, Object>) statement.get("mainsnak");
            Map<String, Object> dataValue = (Map<String, Object>) mainSnak.get("datavalue");
            Map<String, String> value = (Map<String, String>) dataValue.get("value");
            String id = value.get("id");
            if (id.equals(finalName)) {
                result = statement;
                break;
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
        Map<String, Object> entities = (Map<String, Object>) getJson(id).get("entities"); // If I start getting
        // exceptions then so be it!
        Map<String, Object> entity = (Map<String, Object>) entities.get(id);
        Map<String, Object> labels = (Map<String, Object>) entity.get("descriptions");
        Map<String, String> label = (Map<String, String>) labels.get("en"); // TODO: Languages!
        return label.get("value");
    }

    @Override
    public ArrayList<String> getStatementList(String id) {
        Map<String, Object> statements =
                ((Map<String, Map<String, Map<String, Object>>>) getJson(id)
                        .get("entities"))
                        .get(id)
                        .get("claims");
        Set<String> keys = statements.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);
        keyList.sort(Comparator.comparingInt((i) -> Integer.parseInt(i.substring(1))));
        return keyList;
    }

    @Override
    public Value getSingleStatement(ArrayList<String> tree, Datum item, DatumQueryService statementService) {
        String id = tree.get(0);
        Map<String, Object> statements =
                ((Map<String, Map<String, Map<String, Object>>>) getJson(id)
                        .get("entities"))
                        .get(id)
                        .get("claims");

        return new Statement(item, tree.get(1), statementService);
    }

    private String formatURL(String id) {
        return "https://www.wikidata.org/wiki/Special:EntityData/" + id + ".json";
    }

    private Map<String, Object> getJson(String urlStr) {
        URLConnection connection = null;
        try {
            URL url = new URL(formatURL(urlStr));
            connection = url.openConnection();
        } catch (IOException ignored) {
            //ignored
        }

        StringBuilder json = new StringBuilder(1000);
        if (createJsonString(connection, json)) {
            return null;
        }

        Map<String, Object> result = null;
        try {
            result = (Map<String, Object>) engine.eval(json.toString());
        } catch (ScriptException | ClassCastException ignored) {
            //ignored
        } // Something very bad happened!

        return result;
    }

    private boolean createJsonString(URLConnection connection, StringBuilder json) {
        json.append("Java.asJSONCompatible(");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                json.append(line).append('\n');
            }
        } catch (IOException e) {
            return true;
        }
        json.append(")");
        return false;
    }
}
