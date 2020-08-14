package model.data.additional;

import model.data.DatumQueryService;

public class MathematicalExpression extends LiteralString {
    /*
     * Class Description:
     * This is a mathematical expression of some sort in LaTeX format.
     */

    /*
     * REQUIRES: value is valid LaTeX, queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new mathematical expression from the given value
     */
    public MathematicalExpression(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
