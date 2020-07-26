package ui;

import java.util.ArrayList;

public class LayoutManager {
    //TODO: Implement
    private ArrayList<ItemViewController> controllers;

    public void add(ItemViewController newView) {
        controllers.add(newView);
    }

    public boolean remove(ItemViewController toRemove) { // true if ItemViewController is now empty
        controllers.remove(toRemove);
        return controllers.isEmpty();
    }

    public boolean isEmpty() {
        return controllers.isEmpty();
    }

    public int size() {
        return controllers.size();
    }

    //TODO: Rendering stuff
}
