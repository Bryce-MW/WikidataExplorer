package model.data.additional;

import model.data.DatumQueryService;

public class TabularData extends LiteralString {
    /*
     * Class Description:
     * Tabular data is not actually the data itself but a link to some tabular data on Wikimedia Commons. I don't
     * know if this is actually a URL or not so I just have it extend string for now but maybe it should really be a
     * URL.
     */

    public TabularData(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
