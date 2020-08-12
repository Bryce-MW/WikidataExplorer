package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class Statement extends Value {
    /*
     * Class Description:
     *
     */
    private final String name;
    private final Datum about;
    private StatementList statements = null;

    public Statement(Datum item, String property, DatumQueryService queryService) throws NotFoundException {
        super(queryService, property);
        this.queryService = queryService;
        this.name = queryService.getNameByID(property);
        this.about = item;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private ArrayList<Value> findStatements() {
        return queryService.getDatumLinkListByStatement(this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getImage() {
        return statements.getImage();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getTitle() {
        return name;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getDescription() {
        return "";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        if (statements == null) {
            statements = new StatementList(this, queryService, findStatements());
        }
        return statements;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        if (subList.size() == 0) {
            return about.toggleLeft(this);
        }
        return statements.parse(subList);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getParentID() {
        return about.getID();
    }
}
