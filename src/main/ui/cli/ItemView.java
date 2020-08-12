package ui.cli;

import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.Statement;
import model.data.Value;
import model.data.pages.Item;
import model.util.StringBuilderUtil;
import ui.GUInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ItemView extends JPanel {
    /*
     * Class Description:
     * An item view is used in both the GUI and the CLI. In the CLI it is contained within an item view controller.
     * In the GUI, it is its own top level component. This class is larger than it needs to be as it really should be
     *  separated between rendering and functionality. There are also a number of things which should be delegated to
     *  the Items themselves (and perhaps some sort of item rendering class). I also never implemented language
     * support properly but it is not implemented anywhere else so at least it is not inconsistent.
     */
    private final Value item; // Q42, P137, L23 . . .

    // The following will need language support!
    private final String title; // Douglas Addams
    private final String description; // English writer and humorist
    private final SearchBar searchBar;
    private final StatementList statements;
    private ItemViewController controller = null;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ItemView(Value value) {
        this.item = value;
        this.item.setView(this);
        // TODO: Still need to add in language support!
        title = item.getTitle();
        description = item.getDescription();
        searchBar = new SearchBar(new ScopedSearch(item, item.getQuery()));
        statements = item.getStatements();

        configureView();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private void configureView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addButtons();

        JLabel titleComp = new JLabel(title);
        titleComp.setForeground(Color.WHITE);
        add(titleComp);
        JLabel descr = new JLabel(description);
        descr.setForeground(Color.WHITE);
        add(descr);
        if (item.needsSearchBar()) {
            add(searchBar);
        }
        add(statements);
        setBackground(GUInterface.midGray);
        setForeground(Color.WHITE);

        if (item instanceof Statement && item.getID().equals("P18")) {
            try {
                add(new JLabel(new ImageIcon(ImageIO.read(new URL(item.getImage())))));
            } catch (IOException ignored) {
                // Just don't add the image if there isn't one
            }
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private void addButtons() {
        JButton button = new JButton("X");
        int size = (int) button.getMaximumSize().getHeight();
        button.setMaximumSize(new Dimension(size, size));
        button.addActionListener(i -> {
            Container parent = this.getParent();
            parent.remove(this);
            parent.repaint();
        });
        add(button);

        if (item.needsRightArrow()) {
            addRightButton();
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private void addRightButton() {
        JButton button;
        int size;
        button = new JButton("►");
        size = (int) button.getMaximumSize().getHeight();
        button.setMaximumSize(new Dimension(size, size));
        button.addActionListener(i -> {
            Container parent = this.getParent();
            if (parent instanceof GUInterface) {
                GUInterface guInterface = (GUInterface) parent;
                try {
                    guInterface.toggle(new ItemView(new Item(this.item.getID(), this.item.getQuery())));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        add(button);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void updateLanguage(String lang) {
    } // TODO: Languages!

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Value getItem() {
        return item;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(16);

        // First solid line
        StringBuilder itemLine = new StringBuilder(item.getID()).insert(0, '┃');
        result.add(itemLine);
        result.add(new StringBuilder(title).insert(0, '┃'));
        result.add(new StringBuilder(description).insert(0, '┃')); // Will we want to format multi-line?
        // Light line
        if (item.needsSearchBar()) {
            result.add(new StringBuilder(searchBar.toString()).insert(0, '┃'));
        }
        // Light line
        List<StringBuilder> statementLines = statements.toStringArray();
        statementLines.forEach((i) -> i.insert(0, '┃'));
        result.addAll(statementLines);
        // Last solid line

        StringBuilderUtil.padAll(result, ' ', 0);
        result.forEach((i) -> i.append('┃'));
        if (item.needsRightArrow() && itemLine.length() >= 2) {
            itemLine.setCharAt(itemLine.length() - 2, '►');
        }

        addLines(result);

        return result;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private void addLines(ArrayList<StringBuilder> result) {
        int maxLength = result.get(0).length();
        StringBuilder topLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(topLine, '━', maxLength);
        topLine.setCharAt(0, '┏');
        topLine.setCharAt(topLine.length() - 1, '┓');
        result.add(0, topLine);

        StringBuilder midLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(midLine, '─', maxLength);
        midLine.setCharAt(0, '┠');
        midLine.setCharAt(midLine.length() - 1, '┨');
        result.add(4, midLine);
        if (item.needsSearchBar()) {
            result.add(6, midLine);
        }

        StringBuilder botLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(botLine, '━', maxLength);
        botLine.setCharAt(0, '┗');
        botLine.setCharAt(botLine.length() - 1, '┛');
        result.add(botLine);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean parse(List<String> subList) {
        ArrayList<String> command = new ArrayList<>(subList);
        if (command.size() == 0) {
            if (item.needsRightArrow() && controller != null) {
                try {
                    return controller.toggleRight(new Item(item.getID(), item.getQuery()));
                } catch (NotFoundException e) {
                    return false;
                }
            }
            return false;
        }
        if (command.get(0).length() == 1) {
            if (subList.get(0).equals(searchBar.getCommandString())) {
                return searchBar.parse(command.subList(1, command.size()));
            } // Leaving this open for more components in the future
        }
        return statements.parse(command);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean toggleLeft(Value statement) {
        if (controller == null) {
            return false;
        }
        return controller.toggleLeft(statement);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void setController(ItemViewController controller) {
        this.controller = controller;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void toggle(ItemView value) {
        Container parent = getParent();
        if (parent instanceof GUInterface) {
            GUInterface guInterface = (GUInterface) parent;
            guInterface.toggle(value);
        }
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemView)) {
            return false;
        }

        ItemView itemView = (ItemView) o;

        return getItem() == itemView.getItem(); //I want to compare actually being the same item
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public int hashCode() {
        return getItem().hashCode();
    }
}
