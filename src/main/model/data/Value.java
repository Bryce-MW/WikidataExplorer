package model.data;

import ui.ItemView;
import ui.StatementList;

import java.util.List;

public abstract class Value {
    protected DatumQueryService queryService;
    protected ItemView view = null;

    protected Value(DatumQueryService queryService) {
        this.queryService = queryService;
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract String getID();

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

    //TODO: Implement
}
