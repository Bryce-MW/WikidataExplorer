package ui;

import model.data.Value;

import java.util.ArrayList;

public class StatementList {
    //TODO: Implement
    private ArrayList<Value> statements;
    private Value entity; // Entity this statement list refers to.

    public StatementList(Value entity) {
        this.entity = entity;
        statements = new ArrayList<>(10);
        getBasicStatements();
    }

    private void getBasicStatements() {}
}
