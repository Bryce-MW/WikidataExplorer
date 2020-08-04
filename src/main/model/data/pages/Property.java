package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import org.jetbrains.annotations.NotNull;

public class Property extends Datum {
    private final String title;
    private final String description;

    public Property(String id, @NotNull DatumQueryService queryService) throws NotFoundException {
        super(queryService, id);
        this.title = queryService.getNameByID(id);
        this.description = queryService.getDescriptionByID(id);
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
