package ui;

import model.data.Statement;

import java.util.ArrayList;
import java.util.List;

public class ItemViewController {
    //TODO: Implement
    private final ArrayList<ItemView> items;
    private LayoutManager manager;

    public ItemViewController(ItemView initial) {
        items = new ArrayList<>(10);
        items.add(initial);
        initial.setController(this);
    }

    public void add(ItemView newView) {
        items.add(newView);
        newView.setController(this);
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

    public boolean toggleLeft(Statement statement) {
        if (manager == null) {
            return false;
        }
        return manager.toggleLeft(statement, this);
    }

    public void setManager(LayoutManager manager) {
        this.manager = manager;
    }

    public void toggle(Statement statement) {
        if (items.stream().anyMatch((i) -> i.getItem() == statement)) {
            ItemView found = items.stream().filter((i) -> i.getItem() == statement).findAny().orElseThrow(Error::new);
            // I should probably make my own exception class but the idea is that this can't happen. I suppose it's
            // not thread-safe but I don't have anything that is thread-safe.
            items.remove(found);
        } else {
            items.add(new ItemView(statement));
        }
    }

    //TODO: Rendering stuff
}
