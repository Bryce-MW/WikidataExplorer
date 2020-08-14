package model.data.additional;

import model.data.DatumQueryService;
import ui.cli.StatementList;

public class LiteralString extends AbstractAdditional {
    /*
     * Class Description:
     * This represents something that is just a string and is not associated with any specific language.
     */
    protected final String value;

    /*
     * REQUIRES: value is not null, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new literal string from the given value
     */
    public LiteralString(String value, DatumQueryService queryService) {
        super(queryService);
        this.value = value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the title to be displayed
     */
    @Override
    public String getTitle() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the description to be displayed
     */
    @Override
    public String getDescription() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the ID (just the value)
     */
    @Override
    public String getID() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as text has no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
