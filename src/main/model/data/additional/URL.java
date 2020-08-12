package model.data.additional;

import model.data.DatumQueryService;

public class URL extends LiteralString {
    /*
     * Class Description:
     * A URL is just a string which happens to be interpreted as a URL.
     */
    public URL(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
