package model.data.additional;

import model.data.DatumQueryService;

public class MusicalNotation extends LiteralString {
    /*
     * Class Description:
     * Musical notation is a string. According to the Wikidata documentation "Values for that data type are strings
     * describing music following LilyPond syntax"
     */

    public MusicalNotation(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
