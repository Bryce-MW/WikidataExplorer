package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import org.jetbrains.annotations.NotNull;

public class Item extends Datum {
    private final String name;
    private final String description;

    public Item(String id, @NotNull DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        //TODO: Implement
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
