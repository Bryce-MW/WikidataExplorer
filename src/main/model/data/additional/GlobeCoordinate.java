package model.data.additional;

import model.data.DatumQueryService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.StatementList;

public class GlobeCoordinate extends AbstractAdditional {
    private final double posLat;
    private final double posLong;

    public GlobeCoordinate(double posLat, double posLong, DatumQueryService queryService) {
        super(queryService);
        this.posLat = posLat;
        this.posLong = posLong;
    }

    @Override
    public @NotNull String getTitle() {
        return "Position";
    }

    @Override
    public @NotNull String getDescription() {
        return posLat + ", " + posLong;
    }

    @Override
    public @Nullable StatementList getStatements() {
        return null;
    }
}
