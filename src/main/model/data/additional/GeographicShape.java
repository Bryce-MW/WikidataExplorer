package model.data.additional;

import model.data.DatumQueryService;

public class GeographicShape extends LiteralString {

    public GeographicShape(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
