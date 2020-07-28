package model.data.pages.language;

import model.data.DatumQueryService;

public class Form {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Form(String id, DatumQueryService queryService) {
        this.id = id;
        this.queryService = queryService;
    }
}
