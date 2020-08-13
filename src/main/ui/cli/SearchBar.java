package ui.cli;


import model.data.ScopedSearch;
import model.data.Value;
import ui.GUInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SearchBar extends JPanel {
    /*
     * Class Description:
     * The search bar is used both as the top level search bar to add a new item as well as the search bar on an
     * individual item to add statements to the statement list. This class does not do much of the actual searching
     * however I originally intended it to so there are many remnants left over. It does however display the actual
     * search bar either in the menu bar or within the interview itself as a JPanel containing a JTextField.
     */
    private final ScopedSearch searchService;
    private final ArrayList<Value> results;

    /*
     * REQUIRES: searchService is not null
     * MODIFIES: this
     * EFFECTS : sets up a new search bar
     */
    public SearchBar(ScopedSearch searchService) {
        this.searchService = searchService;
        results = new ArrayList<>(50);

        JTextField textField = new JTextField("Search");
        textField.addActionListener((i) -> parse(Collections.singletonList(textField.getText())));
        this.setLayout(new GridLayout());
        add(textField);
        textField.setBackground(GUInterface.brightGray);
        textField.setForeground(Color.WHITE);
        setBackground(GUInterface.midGray);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the results of the last search
     */
    public List<Value> getResults() {
        return results;
    }

    /*
     * REQUIRES: query is not null
     * MODIFIES: this
     * EFFECTS : searches for a specific ID
     */
    public List<Value> search(String query) {
        return searchService.findElement(query);
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : Returns the CLI string for the search bar
     */
    public String toString() {
        return "S: Search";
    }

    /*
     * REQUIRES: subList is not null
     * MODIFIES: this
     * EFFECTS : parses a REPL command directed at this search bar
     */
    public Boolean parse(List<String> subList) {
        if (subList.size() == 0) {
            return false;
        }
        String id = subList.get(0);
        List<Value> values = search(id);
        if (searchService.getItem() != null || searchService.getController() != null) {
            searchService.add(values);
        } else {
            Container parent = getParent();
            if (parent instanceof MenuBar) {
                MenuBar guInterface = (MenuBar) parent;
                values.forEach(i -> guInterface.toggle(new ItemView(i)));
            }
        }
        return false;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the string which is used to select to send commands to this search bar
     */
    public String getCommandString() {
        return "S";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets a reference to the function which parses a REPL command
     */
    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }
}
