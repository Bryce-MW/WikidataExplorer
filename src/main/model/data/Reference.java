package model.data;

import model.data.pages.Property;
import ui.cli.StatementList;

import java.util.List;

public class Reference extends Value {
    /*
     * Class Description:
     * A references is basically how we know some specific statement is true. Only problem is that references can have
     *  multiple parts and we just store each part as a separate reference which can get quite confusing. I mostly
     * ignore this as "good enough" for now.
     */
    private final Property property;
    private final Value value;

    /*
     * REQUIRES: property is not null, value is not null, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates this specific refrence
     */
    public Reference(Property property, Value value, DatumQueryService queryService) {
        super(queryService, property.getID());
        this.property = property;
        this.value = value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : finalizes the UI after it has been initialized
     */
    @Override
    public void addNotify() {
        super.addNotify();
        remove(button);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the property that this reference is using
     */
    public Property getProperty() {
        return property;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : get the value that this reference represents
     */
    public Value getValue() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title of this reference to be displayed
     */
    @Override
    public String getTitle() {
        return property.getTitle() + ": " + value.getTitle();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description of this reference which is to be displayed
     */
    @Override
    public String getDescription() {
        return value.getTitle();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as a reference has no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed to this statement
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
