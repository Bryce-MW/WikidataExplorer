package model.data;

import ui.StatementList;

import java.util.ArrayList;
import java.util.List;

public class DatumLink extends Value {
    private Statement property;
    private Value value;
    private final ArrayList<Qualifier> qualifiers;
    private final ArrayList<Reference> references;

    private final String id;
    private final String name;
    private final String description;

    public DatumLink(DatumQueryService queryService, Statement property, Value value) {
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

    @Override
    public Boolean parse(List<String> subList) {
        return false; // Not implementing this quite yet either
    }
    //TODO: Implement
}
