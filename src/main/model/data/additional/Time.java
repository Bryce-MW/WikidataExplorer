package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.TimeData;
import ui.cli.StatementList;

public class Time extends AbstractAdditional {
    /*
     * Class Description:
     * A time is essentially just a string which is in a specific format to represent time but also has other fields
     * (which I do not use) to represent the accuracy of the given time. For example, the creation of earth has a
     * very low accuracy but the UNIX epoch has a very high accuracy (since it's defined rather than measured). But
     * even other measured times such as the birth of Barack Obama is known to a significantly higher accuracy than
     * when the first life came about.
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
