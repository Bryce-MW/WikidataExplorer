package model.data.additional;

import model.data.DatumQueryService;

public class ExternalIdentifier extends LiteralString {

    public ExternalIdentifier(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
