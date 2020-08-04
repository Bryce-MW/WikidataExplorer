package model.data.additional;

import model.data.DatumQueryService;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

public class Time extends AbstractAdditional {
    protected final String time;

    public Time(String time, DatumQueryService queryService) {
        super(queryService);
        this.time = time;
    }
    // TODO: These are not needed yet

    @Override
    public String getTitle() {
        return time;
    }

    @Override
    public @Nullable String getDescription() {
        return null;
    }

    @Override
    public String getID() {
        return time;
    }

    @Override
    public @Nullable StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
