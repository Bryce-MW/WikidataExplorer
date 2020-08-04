package model.data.additional;

import model.data.DatumQueryService;
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
    public String getTitle() {
        return "Position";
    }

    @Override
    public String getDescription() {
        return posLat + ", " + posLong;
    }

    @Override
    public StatementList getStatements() {
        return null;
    }
}
