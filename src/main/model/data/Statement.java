package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class Statement extends Value {
    private final String name;
    private StatementList statements = null;
    private final Datum about;

    public Statement(Datum item, String property, DatumQueryService queryService) {
        super(queryService, property);
        this.queryService = queryService;
        this.name = queryService.getNameByID(property);
        this.about = item;
    }

    private ArrayList<Value> findStatements() {
        ArrayList<Value> result = new ArrayList<>(10);
        ArrayList<String> tree = new ArrayList<>(2);
        tree.add(about.getID());
        tree.add(getID());
        return queryService.getDatumLinkListByStatement(this);
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
    public StatementList getStatements() {
        if (statements == null) {
            statements = new StatementList(this, queryService, findStatements());
        }
        return statements;
    }

    @Override
    public Boolean parse(List<String> subList) {
        if (subList.size() == 0) {
            return about.toggleLeft(this);
        }
        return statements.parse(subList);
    }
    //TODO: Implement

    public String getParentID() {
        return about.getID();
    }
}
