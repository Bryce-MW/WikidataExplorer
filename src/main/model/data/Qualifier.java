package model.data;

import model.data.pages.Property;
import ui.cli.StatementList;

import java.util.List;

public class Qualifier extends Value {
    //TODO: Implement
    private final Property property;
    private final Value value;

    public Qualifier(Property property, Value value, DatumQueryService queryService) {
        super(queryService, property.getID());
        this.property = property;
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String getTitle() {
        return property.getTitle() + ": " + value.getTitle();
    }

    @Override
    public String getDescription() {
        return value.getTitle();
    }

    @Override
    public StatementList getStatements() {
        return null;
    }

    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
