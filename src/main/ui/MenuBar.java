package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MenuBar {
    //TODO: Implement
    private final ArrayList<MenuBarItem> items;
    private SearchBar searchBar;
    private final int width;

    public MenuBar(ArrayList<MenuBarItem> items, SearchBar searchBar, int width) {
        this.items = new ArrayList<>(5);
        this.items.addAll(items);
        this.searchBar = searchBar;
        this.width = width;
    }

    public void swapSearch(SearchBar searchBar) {
        this.searchBar = searchBar;
    }

    public void addItem(MenuBarItem item) {
        items.add(item);
    }

    public void removeItem(MenuBarItem item) {
        items.remove(item);
    }

    //TODO: Rendering Stuff

    public List<StringBuilder> toStringArray() { // Always returns a length of 3
        //TODO: Implement
        ArrayList<StringBuilder> output = new ArrayList<>(3);
        // We still use ArrayList rather than array even though the size is static because Lists are much easier to
        // use than arrays. Since we never exceed the default capacity (10), the efficiency lost is very small.

        StringBuilder line1 = new StringBuilder(width);
        IntStream.range(0, width).forEach((i) -> line1.append('━'));
        StringBuilder line3 = new StringBuilder(line1.toString());

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
        IntStream.range(0, (width - line2.length() - search.length() - 2)).mapToObj(i -> ' ').forEach(line2::append);
        line2.append('┃');
        line1.setCharAt(line2.length(), '┯');
        line3.setCharAt(line2.length(), '┷');
        line2.append(search);
        line2.append('┃');

        line3.setCharAt(0, '┗');
        line3.setCharAt(line3.length() - 1, '┛');

        output.add(line1);
        output.add(line2);
        output.add(line3);

        return output;
    }
}
