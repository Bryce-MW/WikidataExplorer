package model.data;

import ui.StatementList;

import java.util.ArrayList;

public class DatumLink extends Value {
    private Statement property;
    private Value value;
    private ArrayList<Qualifier> qualifiers;
    private ArrayList<Reference> references;

    private String id;
    private String name;
    private String description;

    protected DatumLink(DatumQueryService queryService, Statement property, Value value) {
        super(queryService);
        id = value.getID();
        name = value.getTitle();
        description = value.getDescription();
        qualifiers = queryService.getQualifiersByStatement(property, value);
        references = queryService.getReferencesByStatement(property, value);
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
