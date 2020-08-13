package model.data;

import model.data.pages.Property;
import ui.cli.StatementList;

import java.util.List;

public class Qualifier extends Value {
    /*
     * Class Description:
     * A qualifier is some condition which a statement is only true under. This is a bit of a nebulous definition
     * though as it can also be used for things like setting a statement order (such as when a person has multiple
     * first names and the one they use the most is selected as the primary one). There are other qualifiers that do
     * weird things as well. It's kinds of hard to explain so I would just have a look at the actual Wikidata website
     *  itself to see what I mean.
     */
    private final Property property;
    private final Value value;

    /*
     * REQUIRES: property is not null, value is not null, queryService is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public Qualifier(Property property, Value value, DatumQueryService queryService) {
        super(queryService, property.getID());
        this.property = property;
        this.value = value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS :
     */
    @Override
    public void addNotify() {
        super.addNotify();
        remove(button);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public Property getProperty() {
        return property;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public Value getValue() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return property.getTitle() + ": " + value.getTitle();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return value.getTitle();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        return null;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
