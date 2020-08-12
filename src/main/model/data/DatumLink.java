package model.data;

import ui.cli.StatementList;

import java.util.ArrayList;
import java.util.List;

public class DatumLink extends Value {
    /*
     * Class Description:
     *
     */
    private final Statement property;
    private final ArrayList<Qualifier> qualifiers;
    private final ArrayList<Reference> references;
    private final String name;
    private final String description;
    private StatementList statementList = null;
    private final Value value;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getImage() {
        return value.getImage();
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
        return description;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public StatementList getStatements() {
        if (statementList == null) {
            ArrayList<Value> statements = new ArrayList<>(qualifiers.size() + references.size());
            statements.addAll(qualifiers);
            statements.addAll(references);
            statementList = new StatementList(this, queryService, statements);
        }
        return statementList;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return property.toggleLeft(this);
    }
}
