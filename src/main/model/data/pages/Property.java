package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Property extends Datum {
    private final String title;
    private final String description;

    public Property(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        this.title = queryService.getNameByID(id);
        this.description = queryService.getDescriptionByID(id);
    }

    public Property(EntityData data, DatumQueryService queryService) throws NotFoundException {
        this(data.id, queryService);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
