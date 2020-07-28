package ui;

import model.data.Value;
import model.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.List;

public class StatementList {
    //TODO: Implement
    private ArrayList<Value> statements;
    private Value entity; // Entity this statement list refers to.

    public StatementList(Value entity) {
        this.entity = entity;
        statements = new ArrayList<>(10);
        getBasicStatements();
    }

    private void getBasicStatements() {} //TODO: Implement

    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(statements.size());
        int maxID = statements.stream().map(Value::getID).mapToInt(String::length).max().orElse(0);
        for (Value statement : statements) {
            String name = statement.getTitle();
            StringBuilder line = new StringBuilder(maxID + name.length() + 1);
            line.append('◄').append(statement.getID()).append(':');
            StringBuilderUtil.pad(line, ' ', maxID);
            line.append(name);
        }

        return result;
    }
}
