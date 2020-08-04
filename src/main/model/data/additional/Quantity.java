package model.data.additional;

import model.data.DatumQueryService;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

public class Quantity extends AbstractAdditional {
    protected Quantity(DatumQueryService queryService) {
        super(queryService);
    }
    // TODO: These are not needed yet

    @Override
    public @Nullable String getTitle() {
        return null;
    }

    @Override
    public @Nullable String getDescription() {
        return null;
    }

    @Override
    public @Nullable String getID() {
        return null;
    }

    @Override
    public @Nullable StatementList getStatements() {
        return null;
    }
    //TODO: Implement
}
