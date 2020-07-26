package ui;

import java.util.ArrayList;

public class MenuBar {
    //TODO: Implement
    private ArrayList<MenuBarItem> items;
    private SearchBar searchBar;

    public MenuBar(ArrayList<MenuBarItem> items, SearchBar searchBar) {
        this.items = new ArrayList<>(5);
        this.items.addAll(items);
        this.searchBar = searchBar;
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
}
