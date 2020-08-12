package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class Statement extends Value {
    /*
     * Class Description:
     * This is a specific statement which is really multiple statements. Basically, an Item can have a number oof
     * statements which are associated with the same property. This acts as a collection of all of these statements
     * which have the same property. Throughout this entire project, I have gone back and forth between proper
     * terminology so this name, and many others, is quite inconstant. Luckily, I have the diagram which I drew
     * before starting which helps me sort out what everything is.
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
