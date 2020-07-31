package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;

public class Property extends Datum {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Property(String id, DatumQueryService queryService) {
        super(queryService, id);
        this.id = id;
        this.queryService = queryService;
    }

    @Override
    public String getTitle() {
        return queryService.getNameByID(id);
    }

    @Override
    public String getDescription() {
        return queryService.getDescriptionByID(id);
    }

    @Override
    public String getID() {
        return id;
    }
}
