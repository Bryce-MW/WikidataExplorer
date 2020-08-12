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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Reference(Property property, Value value, DatumQueryService queryService) {
        super(queryService, property.getID());
        this.property = property;
        this.value = value;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public void addNotify() {
        super.addNotify();
        remove(button);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Property getProperty() {
        return property;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Value getValue() {
        return value;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return property.getTitle() + ": " + value.getTitle();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return value.getTitle();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        return null;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }
}
