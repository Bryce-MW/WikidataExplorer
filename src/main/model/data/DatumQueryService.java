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

    public String getNameByID(String property) {
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
        return collector.getReferences(tree);
    }

    public String getDescriptionByID(String id) {
        return collector.getEntityDescription(id);
    }

    public ArrayList<String> getStatementListByID(String id) {
        return collector.getStatementList(id);
    }

    public Value getStatementByTree(ArrayList<String> tree, Datum item) {
        return collector.getSingleStatement(tree, item, this);
    }

    public ArrayList<Value> getDatumLinkListByStatement(Statement property) {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add(property.getParentID());
        tree.add(property.getID());
        return collector.getDatumLinkListByTree(tree, property, this);
    }
}
