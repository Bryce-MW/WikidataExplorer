package model.data.additional;

import model.data.DatumQueryService;

public class ExternalIdentifier extends LiteralString {
    /*
     * Class Description:
     * This is the ID for the entity in question in some other database since Wikidata is not the only structured
     * database out there.
     */

    public ExternalIdentifier(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
