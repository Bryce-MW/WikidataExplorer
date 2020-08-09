package ui.cli;

import model.data.DatumQueryService;
import model.data.Value;
import model.util.StringBuilderUtil;
import ui.GUInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatementList extends JPanel {
    //TODO: Implement
    private final ArrayList<Value> statements;

    public StatementList(Value entity, DatumQueryService queryService, ArrayList<Value> statements) {
        // Entity this statement list refers to.
        this.statements = statements;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statements.forEach(this::addComp);
        setBackground(GUInterface.midGray);
    }

    private void addComp(JComponent value) {
        add(value);
        revalidate();
    }

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
        addComp(value);
    }

    public void toggle(ItemView value) {
        Container parent = getParent();
        if (parent instanceof ItemView) {
            ItemView guInterface = (ItemView) parent;
            guInterface.toggle(value);
        }
    }
}
