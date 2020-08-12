package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.TimeData;
import ui.cli.StatementList;

public class Time extends AbstractAdditional {
    /*
     * Class Description:
     *
     */
    protected final String time;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Time(String time, DatumQueryService queryService) {
        super(queryService);
        this.time = time;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Time(TimeData timeValue, DatumQueryService queryService) {
        super(queryService);
        this.time = timeValue.time;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return time;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return null;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getID() {
        return time;
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
