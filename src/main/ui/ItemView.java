package ui;

import model.data.ScopedSearch;
import model.data.Statement;
import model.data.Value;
import model.data.pages.Item;
import model.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemView {
    //TODO: Implement
    private final Value item; // Q42, P137, L23 . . .

    // The following will need language support!
    private final String title; // Douglas Addams
    private final String description; // English writer and humorist

    private String photoRef; // May not implement depending on time
    private final SearchBar searchBar; // Only present for Datum! TODO: (add needsSearchBar() to Value, true for Datum,
    // false otherwise.)
    private final StatementList statements;

    private ItemViewController controller = null;

    public ItemView(Value value) {
        this.item = value;
        this.item.setView(this);
        // TODO: Probably won't add language support in first round (Just use english or first language listed)
        title = item.getTitle();
        description = item.getDescription();
        searchBar = new SearchBar(new ScopedSearch(item));
        statements = item.getStatements();
    }

    public void updateLanguage(String lang) {
    } // TODO: Languages!

    public Value getItem() {
        return item;
    }

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
        result.add(6, midLine);

        StringBuilder botLine = new StringBuilder(maxLength);
        StringBuilderUtil.pad(botLine, '━', maxLength);
        botLine.setCharAt(0, '┗');
        botLine.setCharAt(botLine.length() - 1, '┛');
        result.add(botLine);
    }

    public boolean parse(List<String> subList) {
        ArrayList<String> command = new ArrayList<>(subList);
        if (command.size() == 0) {
            if (item.needsRightArrow()) {
                if (controller != null) {
                    return controller.toggleRight(new Item(item.getID(), item.getQuery()));
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

    public boolean toggleLeft(Statement statement) {
        if (controller == null) {
            return false;
        }
        return controller.toggleLeft(statement);
    }

    public void setController(ItemViewController controller) {
        this.controller = controller;
    }

    //TODO: Rendering Stuff
}
