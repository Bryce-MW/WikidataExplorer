package model.data.additional;

import model.data.DatumQueryService;

public class MathematicalExpression extends LiteralString {
    /*
     * Class Description:
     * This is a mathematical expression of some sort in LaTeX format.
     */

    /*
     * REQUIRES: value is valid LaTeX, queryService is not null
     * MODIFIES:
     * EFFECTS :
     */
    public MathematicalExpression(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
