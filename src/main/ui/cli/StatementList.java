package ui.cli;

import model.data.DatumLink;
import model.data.Value;
import model.util.StringBuilderUtil;
import ui.GUInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StatementList extends JPanel {
    /*
     * Class Description:
     * This is a list of statements which is displayed below and item. This acts both as the actual list container
     * and the JPanel which displays the list.
     */
    private final ArrayList<Value> statements;

    /*
     * REQUIRES: statements is not null and contains valid statements about the entity
     * MODIFIES:
     * EFFECTS :
     */
    public StatementList(Value entity, ArrayList<Value> statements) {
        // Entity this statement list refers to.
        this.statements = statements;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statements.forEach(this::addComp);
        setBackground(GUInterface.midGray);
    }

    /*
     * REQUIRES: value is not null
     * MODIFIES:
     * EFFECTS :
     */
    private void addComp(JComponent value) {
        add(value);
        revalidate();
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
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

    /*
     * REQUIRES: command is not null
     * MODIFIES:
     * EFFECTS :
     */
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

    /*
     * REQUIRES: value is not null
     * MODIFIES:
     * EFFECTS :
     */
    public void add(Value value) {
        statements.add(value);
        addComp(value);
    }

    /*
     * REQUIRES: value is not null
     * MODIFIES:
     * EFFECTS :
     */
    public void toggle(ItemView value) {
        Container parent = getParent();
        if (parent instanceof ItemView) {
            ItemView guInterface = (ItemView) parent;
            guInterface.toggle(value);
        }
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public String getImage() {
        for (Value statement : statements) {
            if (statement instanceof DatumLink) {
                if (!statement.getImage().equals("")) {
                    return statement.getImage();
                }
            }
        }
        return "";
    }
}
