package model.data;

import ui.StatementList;

import java.util.ArrayList;

public class Statement extends Value {
    private String id;
    private String name;
    private StatementList statements;
    private Datum about;

    public Statement(Datum item, String property, DatumQueryService queryService) {
        super(queryService);
        this.queryService = queryService;
        this.id = property;
        this.name = queryService.getNameByID(property);
        this.statements = new StatementList(this);
        this.about = item;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public StatementList getStatements() {
        return statements;
    }
    //TODO: Implement

    public String getParentID() {
        return about.getID();
    }
}
