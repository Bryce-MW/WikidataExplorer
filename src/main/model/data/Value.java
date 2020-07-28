package model.data;

import ui.StatementList;

public abstract class Value {
    protected DatumQueryService queryService;

    protected Value(DatumQueryService queryService) {
        this.queryService = queryService;
    }

    public abstract String getTitle();

    public abstract String getDescription();

    public abstract String getID();

    public abstract StatementList getStatements();
    //TODO: Implement
}
