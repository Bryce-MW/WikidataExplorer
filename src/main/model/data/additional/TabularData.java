package model.data.additional;

import model.data.DatumQueryService;

public class TabularData extends LiteralString {

    public TabularData(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
