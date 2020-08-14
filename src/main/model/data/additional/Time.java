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
     * REQUIRES: time is valid time data, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new time from the given time string
     */
    public Time(String time, DatumQueryService queryService) {
        super(queryService);
        this.time = time;
    }

    /*
     * REQUIRES: timeValue is valid TimeData, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new time from the given time data
     */
    public Time(TimeData timeValue, DatumQueryService queryService) {
        super(queryService);
        this.time = timeValue.time;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return time;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return null;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the ID of this time (just the time string)
     */
    @Override
    public String getID() {
        return time;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as time has no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
