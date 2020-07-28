package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import ui.StatementList;

public class Item extends Datum {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Item(String id, DatumQueryService queryService) {
        super(queryService);
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

    @Override
    public StatementList getStatements() {
        return new StatementList(this);
    }
}
