package model.data;

import model.data.source.Collector;

import java.util.ArrayList;

public class DatumQueryService {
    /*
     * Class Description:
     * This class is a service which is used to query for the actual data which is needed. I originally intended for
     * this to actually do a lot of work and have the collectors only do the most basic work of gathering the data
     * and formatting it into a useful object, but it has ended up that I added too many features to the collectors
     * (which is why I had the local collector just extend the web collector) so this class is largely unnecessary.
     */
    private final Collector collector;

    /*
     * REQUIRES: collector is not null
     * MODIFIES: this
     * EFFECTS :
     */
    public DatumQueryService(Collector collector) {
        this.collector = collector;
    }

    /*
     * REQUIRES: item is not null
     * MODIFIES: none
     * EFFECTS :
     */
    public ScopedSearch getScopedSearch(Value item) {
        return new ScopedSearch(item, this);
    }

    /*
     * REQUIRES: property is not null and is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    public String getNameByID(String property) throws NotFoundException {
        return collector.getEntityName(property);
    }

    /*
     * REQUIRES: property is a valid Wikidata ID and is a valid property for the given valid. Value is not null
     * MODIFIES: none
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
     * REQUIRES: property is a valid Wikidata ID and is a valid property for the given valid. Value is not null
     * MODIFIES: none
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
     * REQUIRES: id is not null and is a valid Wikidata ID
     * MODIFIES: none
     * EFFECTS :
     */
    public String getDescriptionByID(String id) throws NotFoundException {
        return collector.getEntityDescription(id);
    }

    /*
     * REQUIRES: id is not null and is a valid Wikidata Id
     * MODIFIES: none
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
     * REQUIRES: tree is a valid tree of IDs and item is a valid item
     * MODIFIES: none
     * EFFECTS :
     */
    public Value getStatementByTree(ArrayList<String> tree, Datum item) throws NotFoundException {
        return collector.getSingleStatement(tree, item, this);
    }

    /*
     * REQUIRES: property is a valid and not null statement
     * MODIFIES: none
     * EFFECTS :
     */
    public ArrayList<Value> getDatumLinkListByStatement(Statement property) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        return collector.getDatumLinkListByTree(tree, property, this);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public Boolean triggerSave() {
        return collector.triggerSave();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS :
     */
    public boolean triggerLoad() {
        return collector.triggerLoad();
    }
}
