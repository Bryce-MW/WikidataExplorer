package ui;

import java.util.ArrayList;

public class ItemViewController {
    //TODO: Implement
    private ArrayList<ItemView> items;

    public ItemViewController(ItemView initial) {
        items = new ArrayList<>(10);
        items.add(initial);
    }

    public void add(ItemView newView) {
        items.add(newView);
    }

    public boolean remove(ItemView toRemove) { // true if ViewController is now empty
        items.remove(toRemove);
        return items.isEmpty();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    //TODO: Rendering stuff
}
