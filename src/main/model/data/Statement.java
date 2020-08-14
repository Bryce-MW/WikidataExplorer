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
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : finds teh list of statements about this statement (that makes no sense haha)
     */
    private ArrayList<Value> findStatements() {
        return queryService.getDatumLinkListByStatement(this);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns teh image for this statement if there is one
     */
    @Override
    public String getImage() {
        return statements.getImage();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the title to be printed
     */
    @Override
    public String getTitle() {
        return name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the description to be printed
     */
    @Override
    public String getDescription() {
        return "";
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : returns the statements which this statement has (again, what . . . I need better terminology)
     */
    @Override
    public StatementList getStatements() {
        if (statements == null) {
            statements = new StatementList(this, findStatements());
        }
        return statements;
    }

    /*
     * REQUIRES: subList is not null
     * MODIFIES: this
     * EFFECTS : parses a REPL command directed at this statement
     */
    @Override
    public Boolean parse(List<String> subList) {
        if (subList.size() == 0) {
            return about.toggleLeft(this);
        }
        return statements.parse(subList);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the ID of the item which this statement is about
     */
    public String getParentID() {
        return about.getID();
    }
}
