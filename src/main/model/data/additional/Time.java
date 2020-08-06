package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.TimeData;
import ui.cli.StatementList;

public class Time extends AbstractAdditional {
    protected final String time;

    public Time(String time, DatumQueryService queryService) {
        super(queryService);
        this.time = time;
    }

    public Time(TimeData timeValue, DatumQueryService queryService) {
        super(queryService);
        this.time = timeValue.time;
    }
    // TODO: These are not needed yet

    @Override
    public String getTitle() {
        return time;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getID() {
        return time;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
