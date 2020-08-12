package ui.cli;

import ui.GUInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MenuBar extends JMenuBar {
    /*
     * Class Description:
     *
     */
    private final ArrayList<MenuBarItem> items;
    private final int width;
    private SearchBar searchBar;
    private GUInterface guInterface;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public MenuBar(ArrayList<MenuBarItem> items, SearchBar searchBar, int width) {
        this.items = items;
        this.searchBar = searchBar;
        this.width = width;

        items.forEach(this::add);
        add(searchBar);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void swapSearch(SearchBar searchBar) {
        this.searchBar = searchBar;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void addItem(MenuBarItem item) {
        items.add(item);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void removeItem(MenuBarItem item) {
        items.remove(item);
    }


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<StringBuilder> toStringArray() { // Always returns a length of 3
        ArrayList<StringBuilder> output = new ArrayList<>(3);
        // We still use ArrayList rather than array even though the size is static because Lists are much easier to
        // use than arrays. Since we never exceed the default capacity (10), the efficiency lost is very small.

        StringBuilder line1 = new StringBuilder(width);
        IntStream.range(0, width).forEach((i) -> line1.append('━'));
        StringBuilder line3 = new StringBuilder(line1);

        line1.setCharAt(0, '┏');
        line1.setCharAt(width - 1, '┓');

        StringBuilder line2 = new StringBuilder(width);
        line2.append('┃');
        for (MenuBarItem item : items) {
            line2.append(item.toString());
            line2.append('│');
            line1.setCharAt(line2.length() - 1, '┯');
            line3.setCharAt(line2.length() - 1, '┷');
        }
        String search = searchBar.toString();
        addLines(line1, line3, line2, search);

        line3.setCharAt(0, '┗');
        line3.setCharAt(line3.length() - 1, '┛');


        output.add(line1);
        output.add(line2);
        output.add(line3);

        return output;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    private void addLines(StringBuilder line1, StringBuilder line3, StringBuilder line2, String search) {
        IntStream.range(0, (width - line2.length() - search.length() - 2)).mapToObj(i -> ' ').forEach(line2::append);
        line2.append('│');
        line1.setCharAt(line2.length() - 1, '┯');
        line3.setCharAt(line2.length() - 1, '┷');
        line2.append(search);
        line2.append('┃');
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Boolean parse(List<String> subList) {
        ArrayList<String> command = new ArrayList<>(subList);
        HashMap<String, Function<List<String>, Boolean>> parsers = new HashMap<>(items.size());
        items.forEach((i) -> parsers.put(i.getCommandString(), i.getParser()));
        parsers.put(searchBar.getCommandString(), searchBar.getParser());

        if (!parsers.containsKey(command.get(0))) {
            return false;
        }

        return parsers.get(command.get(0)).apply(command.subList(1, command.size()));
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void setGuInterface(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void toggle(ItemView itemView) {
        if (guInterface != null) {
            guInterface.toggle(itemView);
        }
    }
}
