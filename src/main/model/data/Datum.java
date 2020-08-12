package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public abstract class Datum extends Value {
    /*
     * Class Description:
     *
     */
    protected final StatementList statements;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    protected Datum(DatumQueryService queryService, String id) {
        super(queryService, id);
        this.statements = new StatementList(this, queryService, findStatements());
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
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
            try {
                result.add(queryService.getStatementByTree(tree, this));
            } catch (NotFoundException ignored) {
                // We just couldn't find that statement so we won't add it
            }
            tree.remove(1);
        }
        return result;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        return statements;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public boolean needsSearchBar() {
        return true;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public boolean needsRightArrow() {
        return false;
    }
}
