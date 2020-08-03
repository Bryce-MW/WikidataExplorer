package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public abstract class Datum extends Value {
    protected StatementList statements;

    protected Datum(DatumQueryService queryService, String id) {
        super(queryService, id);
        this.statements = new StatementList(this, queryService, findStatements());
    }

    protected ArrayList<Value> findStatements() {
        ArrayList<String> statementNames = queryService.getStatementListByID(getID());
        ArrayList<Value> result = new ArrayList<>(10);
        int max = 10;
        if (statementNames.size() < 10) {
            max = statementNames.size();
        }
        ArrayList<String> tree = new ArrayList<>(2);
        tree.add(getID());
        for (String s : statementNames.subList(0, max)) {
            tree.add(s);
            result.add(queryService.getStatementByTree(tree, this));
            tree.remove(1);
        }
        return result;
    }

    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }

    @Override
    public StatementList getStatements() {
        return statements;
    }

    @Override
    public boolean needsSearchBar() {
        return true;
    }

    @Override
    public boolean needsRightArrow() {
        return false;
    }

    //TODO: Implement
}
