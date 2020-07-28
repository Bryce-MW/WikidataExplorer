package model.data;

import model.data.pages.Property;

public class Qualifier {
    //TODO: Implement
    private final Property property;
    private final Value value;

    public Qualifier(Property property, Value value) {
        this.property = property;
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public Value getValue() {
        return value;
    }
}
