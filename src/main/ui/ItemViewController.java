package ui;

import java.util.ArrayList;
import java.util.List;

public class ItemViewController {
    //TODO: Implement
    private final ArrayList<ItemView> items;

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

    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(10 * items.size());
        items.stream().map(ItemView::toStringArray).forEach(result::addAll);
        return result;
    }

    public Boolean parse(List<String> instructions) {
        ArrayList<String> command = new ArrayList<>(instructions);
        return items.stream().filter((i) -> i.getItem().getID().equals(command.get(0)))
                .anyMatch((i) -> i.parse(command.subList(1, command.size())));
    }

    //TODO: Rendering stuff
}
