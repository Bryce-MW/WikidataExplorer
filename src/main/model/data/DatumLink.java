package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class DatumLink extends Value {
    private final Statement property;
    private final Value value;
    private final ArrayList<Qualifier> qualifiers;
    private final ArrayList<Reference> references;
    private StatementList statementList = null;

    private final String name;
    private final String description;

    public DatumLink(DatumQueryService queryService, Statement property, Value value) {
        super(queryService, value.getID());
        this.property = property;
        this.value = value;
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
    public StatementList getStatements() {
        if (statementList == null) {
            ArrayList<Value> statements = new ArrayList<>(qualifiers.size() + references.size());
            statements.addAll(qualifiers);
            statements.addAll(references);
            statementList = new StatementList(this, queryService, statements);
        }
        return statementList;
    }

    @Override
    public Boolean parse(List<String> subList) {
        return property.toggleLeft(this);
    }
    //TODO: Implement
}
