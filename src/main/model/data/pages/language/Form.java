package model.data.pages.language;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;

public class Form extends Datum {
    private final String name;
    private final String description;

    public Form(String id, DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        name = queryService.getNameByID(id);
        description = queryService.getDescriptionByID(id);
    }

    public Form(EntityData data, DatumQueryService queryService) throws NotFoundException {
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
