package model.data.additional;

import model.data.DatumQueryService;
import ui.cli.StatementList;

public class LiteralString extends AbstractAdditional {
    /*
     * Class Description:
     *
     */
    protected final String value;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public LiteralString(String value, DatumQueryService queryService) {
        super(queryService);
        this.value = value;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return value;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return value;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getID() {
        return value;
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
}
