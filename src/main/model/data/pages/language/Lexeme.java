package model.data.pages.language;

import model.data.DatumQueryService;

public class Lexeme {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Lexeme(String id, DatumQueryService queryService) {
        this.id = id;
        this.queryService = queryService;
    }
}
