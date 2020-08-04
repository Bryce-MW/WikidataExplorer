package model.data;

import model.data.source.Collector;

import java.util.ArrayList;

public class DatumQueryService {
    //TODO: Implement
    private final Collector collector;

    public DatumQueryService(Collector collector) {
        this.collector = collector;
    }

    public ScopedSearch getScopedSearch(Value item) {
        return new ScopedSearch(item, this);
    }

    public String getNameByID(String property) throws NotFoundException {
        return collector.getEntityName(property);
    }

    public ArrayList<Qualifier> getQualifiersByStatement(Statement property, Value value) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        tree.add(value.getID());
        return collector.getQualifiers(tree, this);
    }

    public ArrayList<Reference> getReferencesByStatement(Statement property, Value value) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        tree.add(value.getID());
        return collector.getReferences(tree, property.queryService);
    }

    public String getDescriptionByID(String id) throws NotFoundException {
        return collector.getEntityDescription(id);
    }

    public ArrayList<String> getStatementListByID(String id) {
        try {
            return collector.getStatementList(id);
        } catch (NotFoundException e) {
            // Couldn't find statements so just returning an empty list
            return new ArrayList<>(0);
        }
    }

    public Value getStatementByTree(ArrayList<String> tree, Datum item) throws NotFoundException {
        return collector.getSingleStatement(tree, item, this);
    }

    public ArrayList<Value> getDatumLinkListByStatement(Statement property) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        return collector.getDatumLinkListByTree(tree, property, this);
    }

    public Boolean triggerSave() {
        return collector.triggerSave();
    }

    public boolean triggerLoad() {
        return collector.triggerLoad();
    }
}
