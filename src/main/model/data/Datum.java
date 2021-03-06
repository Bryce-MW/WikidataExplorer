package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public abstract class Datum extends Value {
    /*
     * Class Description:
     * This is superclass of anything which has an actual URI page on Wikidata which can be visited. It's possible
     * that other values do as well and I just didn't realize it but these are all of the things with their own top
     * level ID (if you can call the ID that a sense has separate which it really isnt but not in the same was as the
     *  other datatypes).
     */
    protected final StatementList statements;

    /*
     * REQUIRES: queryService is not null, ID is a valid Wikidata ID
     * MODIFIES: this
     * EFFECTS : sets up the fields which are relevant to all Datum
     */
    protected Datum(DatumQueryService queryService, String id) {
        super(queryService, id);
        this.statements = new StatementList(this, findStatements());
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : finds the statements for this Datum
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
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed at this datum
     */
    @Override
    public Boolean parse(List<String> subList) {
        return false;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the statements about this datum
     */
    @Override
    public StatementList getStatements() {
        return statements;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns if this datum needs a search bar
     */
    @Override
    public boolean needsSearchBar() {
        return true;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns if this datum needs a right arrow
     */
    @Override
    public boolean needsRightArrow() {
        return false;
    }
}
