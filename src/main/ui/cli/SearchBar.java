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
     *
     */
    private final ScopedSearch searchService;
    private final ArrayList<Value> results;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<Value> getResults() {
        return results;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<Value> search(String query) {
        return searchService.findElement(query);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String toString() {
        return "S: Search";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getCommandString() {
        return "S";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }
}
