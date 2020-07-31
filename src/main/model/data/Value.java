package model.data;

import model.data.additional.Time;
import model.data.pages.Item;
import model.data.pages.Property;
import ui.ItemView;
import ui.StatementList;

import java.util.List;
import java.util.Map;

public abstract class Value {
    protected DatumQueryService queryService;
    protected ItemView view = null;
    protected String id;

    protected Value(DatumQueryService queryService, String id) {
        this.id = id;
        this.queryService = queryService;
    }

    public static Value parseData(Object value, String dataType, DatumQueryService queryService) {
        Map<String, Object> result = (Map<String, Object>) value;
        switch (dataType) {
            case "wikibase-item":
                return new Item((String) result.get("id"), queryService);
            case "time":
                return new Time((String) result.get("time"), queryService); // Not dealing with calendar models, etc
            case "wikibase-property":
                return new Property((String) result.get("id"), queryService);
        }
        throw new Error("Datatype: " + dataType + " not found");
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

    //TODO: Implement
}
