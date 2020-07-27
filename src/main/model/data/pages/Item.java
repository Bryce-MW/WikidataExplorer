package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;

public class Item extends Datum {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Item(String id, DatumQueryService queryService) {
        this.id = id;
        this.queryService = queryService;
    }
}
