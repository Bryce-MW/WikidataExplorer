package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;

public class Item extends Datum {
    //TODO: Implement
    private final DatumQueryService queryService;
    private final String name;
    private final String description;

    public Item(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        this.queryService = queryService;
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
