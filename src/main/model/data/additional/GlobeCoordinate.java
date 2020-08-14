package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.GlobeCoordinateData;
import ui.cli.StatementList;

public class GlobeCoordinate extends AbstractAdditional {
    /*
     * Class Description:
     * This class represents a specific location on earth (by default but another "globe" can be specified) in some
     * coordinate system.
     */
    private final double posLat;
    private final double posLong;

    /*
     * REQUIRES: posLat and posLong are positions on Earth (or other globe), queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new globe coordinate from the given position data
     */
    public GlobeCoordinate(double posLat, double posLong, DatumQueryService queryService) {
        super(queryService);
        this.posLat = posLat;
        this.posLong = posLong;
    }

    /*
     * REQUIRES: globeCoordinateValue is valid GlobeCoordinateData, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new globe coordinate from the given position data
     */
    public GlobeCoordinate(GlobeCoordinateData globeCoordinateValue, DatumQueryService queryService) {
        super(queryService);
        this.posLat = globeCoordinateValue.latitude;
        this.posLong = globeCoordinateValue.longitude;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return "Position";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return posLat + ", " + posLong;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns null as positions have no statements
     */
    @Override
    public StatementList getStatements() {
        return null;
    }
}
