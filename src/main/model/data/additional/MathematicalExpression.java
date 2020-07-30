package model.data.additional;

import model.data.DatumQueryService;
import ui.StatementList;

public class MathematicalExpression extends AbstractAdditional {
    protected MathematicalExpression(DatumQueryService queryService) {
        super(queryService);
    }
    // TODO: These are not needed yet

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
//TODO: Implement
}
