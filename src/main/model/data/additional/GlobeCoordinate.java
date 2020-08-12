package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.GlobeCoordinateData;
import ui.cli.StatementList;

public class GlobeCoordinate extends AbstractAdditional {
    /*
     * Class Description:
     *
     */
    private final double posLat;
    private final double posLong;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public GlobeCoordinate(double posLat, double posLong, DatumQueryService queryService) {
        super(queryService);
        this.posLat = posLat;
        this.posLong = posLong;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public GlobeCoordinate(GlobeCoordinateData globeCoordinateValue, DatumQueryService queryService) {
        super(queryService);
        this.posLat = globeCoordinateValue.latitude;
        this.posLong = globeCoordinateValue.longitude;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return "Position";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return posLat + ", " + posLong;
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
