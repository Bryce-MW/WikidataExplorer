package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class DatumLink extends Value {
    /*
     * Class Description:
     * This class actually represents a single individual statement. It also contains the list of qualifiers and
     * references for that specific statement.
     */
    private final Statement property;
    private final ArrayList<Qualifier> qualifiers;
    private final ArrayList<Reference> references;
    private final String name;
    private final String description;
    private StatementList statementList = null;
    private final Value value;

    /*
     * REQUIRES: queryService is not null, property is not null, value is not null
     * MODIFIES: this
     * EFFECTS : creates a new datum link
     */
    public DatumLink(DatumQueryService queryService, Statement property, Value value) {
        super(queryService, value.getID());
        this.property = property;
        name = value.getTitle();
        description = value.getDescription();
        qualifiers = queryService.getQualifiersByStatement(property, value);
        references = queryService.getReferencesByStatement(property, value);
        this.value = value;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the image for this datum link if there is one
     */
    @Override
    public String getImage() {
        return value.getImage();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the title to be displayed
     */
    @Override
    public String getTitle() {
        return name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the description to be displayed
     */
    @Override
    public String getDescription() {
        return description;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the list of statements for this datum link
     */
    @Override
    public StatementList getStatements() {
        if (statementList == null) {
            ArrayList<Value> statements = new ArrayList<>(qualifiers.size() + references.size());
            statements.addAll(qualifiers);
            statements.addAll(references);
            statementList = new StatementList(this, statements);
        }
        return statementList;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed at this datum link
     */
    @Override
    public Boolean parse(List<String> subList) {
        return property.toggleLeft(this);
    }
}
