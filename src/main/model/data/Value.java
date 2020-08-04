package model.data;

import model.data.additional.GlobeCoordinate;
import model.data.additional.LiteralString;
import model.data.additional.Time;
import model.data.pages.Item;
import model.data.pages.Property;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.ItemView;
import ui.cli.StatementList;

import java.util.List;
import java.util.Map;

public abstract class Value {
    protected DatumQueryService queryService;
    protected @Nullable ItemView view = null;
    protected final String id;

    protected Value(DatumQueryService queryService, String id) {
        this.id = id;
        this.queryService = queryService;
    }

    @SuppressWarnings("CheckStyle") // Nothing can really be done in this case. It basically has to be long for the
    // switch
    public static @NotNull Value parseData(Object value, @NotNull String dataType, @NotNull DatumQueryService queryService) {
        Map<String, Object> result;
        switch (dataType) {
            case "wikibase-item":
                result = (Map<String, Object>) value;
                try {
                    return new Item((String) result.get("id"), queryService);
                } catch (NotFoundException e) {
                    throw new Error("Parsed Datum " + result.get("id") + " does not exist? (or no data)", e);
                }
            case "time":
                result = (Map<String, Object>) value;
                return new Time((String) result.get("time"), queryService); // Not dealing with calendar models, etc
            case "wikibase-property":
                result = (Map<String, Object>) value;
                try {
                    return new Property((String) result.get("id"), queryService);
                } catch (NotFoundException e) {
                    throw new Error("Parsed Datum" + result.get("id") + "does not exist? (or no data)", e);
                }
            case "globe-coordinate":
                result = (Map<String, Object>) value;
                return new GlobeCoordinate((double) result.get("latitude"), (double) result.get("longitude"),
                        queryService);
            case "string":
                return new LiteralString((String) value, queryService);
        }
        throw new Error("Datatype: " + dataType + " not found");
    }

    public abstract @Nullable String getTitle();

    public abstract @Nullable String getDescription();

    public @Nullable String getID() {
        return id;
    }

    public abstract @Nullable StatementList getStatements();

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
