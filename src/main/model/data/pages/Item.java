package model.data.pages;

import model.data.Datum;
import model.data.DatumQueryService;
import ui.StatementList;

public class Item extends Datum {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;
    private final StatementList statements;

    public Item(String id, DatumQueryService queryService) {
        super(queryService);
        this.id = id;
        this.queryService = queryService;
        this.statements = new StatementList(this, queryService);
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
        return statements;
    }
}
