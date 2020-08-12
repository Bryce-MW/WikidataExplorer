package model.data;

import model.data.source.Collector;

import java.util.ArrayList;

public class DatumQueryService {
    /*
     * Class Description:
     *
     */
    private final Collector collector;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public DatumQueryService(Collector collector) {
        this.collector = collector;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ScopedSearch getScopedSearch(Value item) {
        return new ScopedSearch(item, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getNameByID(String property) throws NotFoundException {
        return collector.getEntityName(property);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ArrayList<Qualifier> getQualifiersByStatement(Statement property, Value value) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        tree.add(value.getID());
        return collector.getQualifiers(tree, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ArrayList<Reference> getReferencesByStatement(Statement property, Value value) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        tree.add(value.getID());
        return collector.getReferences(tree, property.queryService);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getDescriptionByID(String id) throws NotFoundException {
        return collector.getEntityDescription(id);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ArrayList<String> getStatementListByID(String id) {
        try {
            return collector.getStatementList(id);
        } catch (NotFoundException e) {
            // Couldn't find statements so just returning an empty list
            return new ArrayList<>(0);
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Value getStatementByTree(ArrayList<String> tree, Datum item) throws NotFoundException {
        return collector.getSingleStatement(tree, item, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ArrayList<Value> getDatumLinkListByStatement(Statement property) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        return collector.getDatumLinkListByTree(tree, property, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Boolean triggerSave() {
        return collector.triggerSave();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean triggerLoad() {
        return collector.triggerLoad();
    }
}
