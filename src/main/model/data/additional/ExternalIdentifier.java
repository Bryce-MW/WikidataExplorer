package model.data.additional;

import model.data.DatumQueryService;

public class ExternalIdentifier extends LiteralString {
    /*
     * Class Description:
     *
     */

    public ExternalIdentifier(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
