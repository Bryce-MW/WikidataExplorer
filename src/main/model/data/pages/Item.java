package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Item extends Datum {
    private final String name;
    private final String description;

    public Item(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    public Item(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
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
