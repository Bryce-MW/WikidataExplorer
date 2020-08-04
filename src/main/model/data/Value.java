package model.data;

import model.data.additional.*;
import model.data.pages.Item;
import model.data.pages.Property;
import org.jetbrains.annotations.NotNull;
import ui.cli.ItemView;
import ui.cli.StatementList;

import java.util.List;
import java.util.Map;

public abstract class Value {
    protected DatumQueryService queryService;
    protected ItemView view = null;
    protected final String id;

    protected Value(DatumQueryService queryService, String id) {
        this.id = id;
        this.queryService = queryService;
    }

    public static Value parseData(Object value, String dataType, DatumQueryService queryService) {
        Map<String, Object> result;
        switch (dataType) {
            case "wikibase-item":
                return buildItem((Map<String, Object>) value, queryService);
            case "time":
                result = (Map<String, Object>) value;
                return new Time((String) result.get("time"), queryService); // Not dealing with calendar models, etc
            case "wikibase-property":
                return buildProperty((Map<String, Object>) value, queryService);
            case "globe-coordinate":
                return buildGlobeCoordinate((Map<String, Object>) value, queryService);
            case "string":
                return new LiteralString((String) value, queryService);
            case "external-id":
                return new ExternalIdentifier((String) value, queryService);
            case "url":
                return new URL((String) value, queryService);
            case "monolingualtext":
                return buildMonolingualText((Map<String, Object>) value, queryService);
        }
        throw new Error("Datatype: " + dataType + " not found");
    }

    @NotNull
    private static MonolingualText buildMonolingualText(Map<String, Object> value, DatumQueryService queryService) {
        Map<String, Object> result;
        result = value;
        return new MonolingualText((String) result.get("value"), (String) result.get("language"), queryService);
    }

    @NotNull
    private static GlobeCoordinate buildGlobeCoordinate(Map<String, Object> value, DatumQueryService queryService) {
        Map<String, Object> result;
        result = value;
        return new GlobeCoordinate((double) result.get("latitude"), (double) result.get("longitude"),
                queryService);
    }

    @NotNull
    private static Property buildProperty(Map<String, Object> value, DatumQueryService queryService) {
        Map<String, Object> result;
        result = value;
        try {
            return new Property((String) result.get("id"), queryService);
        } catch (NotFoundException e) {
            throw new Error("Parsed Datum" + result.get("id") + "does not exist? (or no data)", e);
        }
    }

    @NotNull
    private static Item buildItem(Map<String, Object> value, DatumQueryService queryService) {
        Map<String, Object> result;
        result = value;
        try {
            return new Item((String) result.get("id"), queryService);
        } catch (NotFoundException e) {
            throw new Error("Parsed Datum " + result.get("id") + " does not exist? (or no data)", e);
        }
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public String getID() {
        return id;
    }

    public abstract StatementList getStatements();

    public abstract Boolean parse(List<String> subList);

    public void setView(ItemView view) {
        this.view = view;
    }

    public boolean needsSearchBar() {
        return false;
    }

    public boolean needsRightArrow() {
        return true;
    }

    public DatumQueryService getQuery() {
        return queryService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Value)) {
            return false;
        }

        Value value = (Value) o;

        return getID().equals(value.getID());
    }

    @Override
    public int hashCode() {
        int result = getDescription().hashCode();
        result = 31 * result;
        return result;
    }

    protected Boolean toggleLeft(Value value) {
        if (this.view == null) {
            return false;
        }
        return view.toggleLeft(value);
    }

    public void addStatement(Value value) {
        getStatements().add(value);
    }


    //TODO: Implement
}
