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
     * MODIFIES:
     * EFFECTS :
     */
    public LiteralString(String value, DatumQueryService queryService) {
        super(queryService);
        this.value = value;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getID() {
        return value;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
