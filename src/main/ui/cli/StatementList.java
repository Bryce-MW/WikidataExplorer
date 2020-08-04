package ui.cli;

import model.data.DatumQueryService;
import model.data.Value;
import model.util.StringBuilderUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

    public @NotNull List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(statements.size());
        int maxID = statements.stream().map(Value::getID).mapToInt(String::length).max().orElse(0);
        for (Value statement : statements) {
            String name = statement.getTitle();
            StringBuilder line = new StringBuilder(maxID + name.length() + 1);
            line.append("◄ ").append(statement.getID()).append(':');
            StringBuilderUtil.pad(line, ' ', maxID);
            line.append(name);
            result.add(line);
        }

        return result;
    }

    public Boolean parse(@NotNull List<String> command) {
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
