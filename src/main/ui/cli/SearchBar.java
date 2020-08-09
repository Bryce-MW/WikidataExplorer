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
    //TODO: Implement
    private final ScopedSearch searchService;
    private final ArrayList<Value> results;

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

    public List<Value> getResults() {
        return results;
    }

    public List<Value> search(String query) {
        return searchService.findElement(query);
    }

    //TODO: Rendering Stuff

    public String toString() {
        return "S: Search";
    }

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

    public String getCommandString() {
        return "S";
    }

    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }
}
