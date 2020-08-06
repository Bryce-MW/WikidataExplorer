package model.data.additional;

import model.data.DatumQueryService;

public class MathematicalExpression extends LiteralString {

    public MathematicalExpression(String value, DatumQueryService queryService) {
        super(value, queryService);
    }
}
