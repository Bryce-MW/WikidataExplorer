package model.data.additional;

import model.data.DatumQueryService;
import ui.StatementList;

public class LiteralString extends AbstractAdditional {
    private final String value;

    public LiteralString(String value, DatumQueryService queryService) {
        super(queryService);
        this.value = value;
    }
    // TODO: These are not needed yet

    @Override
    public String getTitle() {
        return value;
    }

    @Override
    public String getDescription() {
        return value;
    }

    @Override
    public String getID() {
        return value;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
//TODO: Implement
}
