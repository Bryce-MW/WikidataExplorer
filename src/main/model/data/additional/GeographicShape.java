package model.data.additional;

import model.data.DatumQueryService;

public class GeographicShape extends LiteralString {
    /*
     * Class Description:
     * This class represents some sort of geographic shape as a string. I don't know exactly what the format is. I
     * think it might be a link to some other data location for storage of the actual shape.
     */

    public GeographicShape(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
