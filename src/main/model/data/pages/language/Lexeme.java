package model.data.pages.language;

import model.data.Datum;
import model.data.DatumQueryService;
import ui.StatementList;

public class Lexeme extends Datum {
    //TODO: Implement
    private final String id;
    private final DatumQueryService queryService;

    public Lexeme(String id, DatumQueryService queryService) {
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
