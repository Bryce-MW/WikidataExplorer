package model.data;

import ui.StatementList;

import java.util.List;

public class Statement extends Value {
    private final String id;
    private final String name;
    private final StatementList statements;
    private final Datum about;

    public Statement(Datum item, String property, DatumQueryService queryService) {
        super(queryService);
        this.queryService = queryService;
        this.id = property;
        this.name = queryService.getNameByID(property);
        this.statements = new StatementList(this, queryService);
        this.about = item;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public StatementList getStatements() {
        return statements;
    }

    @Override
    public Boolean parse(List<String> subList) {
        if (subList.size() == 0) {
            return about.toggleLeft(this);
        }
        return false;
    }
    //TODO: Implement

    public String getParentID() {
        return about.getID();
    }
}
