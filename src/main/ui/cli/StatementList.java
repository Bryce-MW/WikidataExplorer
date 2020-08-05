package ui.cli;

import model.data.DatumQueryService;
import model.data.Value;
import model.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatementList {
    //TODO: Implement
    private final ArrayList<Value> statements;

    public StatementList(Value entity, DatumQueryService queryService, ArrayList<Value> statements) {
        // Entity this statement list refers to.
        this.statements = statements;
    }

    // Currently unused but kept just in case
//    private void getBasicStatements() {
//        if (entity instanceof Datum) {
//            ArrayList<String> statementNames = queryService.getStatementListByID(entity.getID());
//            int max = 10;
//            if (statementNames.size() < 10) {
//                max = statementNames.size();
//            }
//            ArrayList<String> tree = new ArrayList<>(2);
//            tree.add(entity.getID());
//            for (String s : statementNames.subList(0, max)) {
//                tree.add(s);
//                statements.add(queryService.getStatementByTree(tree, (Datum) entity));
//                tree.remove(1);
//            }
//        }
//    }

    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(statements.size());
        AtomicInteger maxID = new AtomicInteger(); // Must be atomic due to the lambda usage
        statements.stream().map(Value::getID).mapToInt(String::length)
                .forEach((i) -> maxID.set(Math.max(i, maxID.get())));
        for (Value statement : statements) {
            String name = statement.getTitle();
            StringBuilder line = new StringBuilder(maxID.get() + 4);
            line.append("◄ ").append(statement.getID()).append(": ");
            StringBuilderUtil.pad(line, ' ', maxID.get() + 4);
            line.append(name);
            result.add(line);
        }

        return result;
    }

    public Boolean parse(List<String> command) {
        ArrayList<String> instruction = new ArrayList<>(command);
        if (instruction.size() == 2 && instruction.get(1).equals("R")) {
            ArrayList<Value> toRemove = new ArrayList<>(1);
            Boolean remove = statements.stream()
                    .filter((i) -> i.getID().equals(instruction.get(0)))
                    .anyMatch(toRemove::add);
            toRemove.forEach(statements::remove);
            return remove;
        }
        return statements.stream()
                .filter((i) -> i.getID().equals(instruction.get(0)))
                .anyMatch((i) -> i.parse(instruction.subList(1, instruction.size())));
    }

    public void add(Value value) {
        statements.add(value);
    }
}
