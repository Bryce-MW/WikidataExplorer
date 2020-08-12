package ui.cli;

import model.data.Value;

import java.util.ArrayList;
import java.util.List;

public class ItemViewController {
    /*
     * Class Description:
     * This stores an individual row of items. It should never be empty as an item is added right at the start and
     * whenever an item is removed, the remove method returns true as to indicate that this item view controller
     * should be deleted.
     */
    private final ArrayList<ItemView> items;
    private LayoutManager manager;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ItemViewController(ItemView initial) {
        items = new ArrayList<>(10);
        items.add(initial);
        initial.setController(this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void add(ItemView newView) {
        items.add(newView);
        newView.setController(this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean remove(ItemView toRemove) { // true if ViewController is now empty
        items.remove(toRemove);
        return items.isEmpty();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public int size() {
        return items.size();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(10 * items.size());
        items.stream().map(ItemView::toStringArray).forEach(result::addAll);
        return result;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public Boolean parse(List<String> instructions) {
        boolean found = false;
        ArrayList<String> command = new ArrayList<>(instructions);
        String id = command.get(0);
        for (ItemView item : items) {
            if (item.getItem().getID().equals(id)) {
                found |= item.parse(command.subList(1, command.size()));
            }
        }
        return found;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean toggleLeft(Value statement) {
        if (manager == null) {
            return false;
        }
        return manager.toggleLeft(statement, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean toggleRight(Value statement) {
        if (manager == null) {
            return false;
        }
        return manager.toggleRight(statement, this);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void setManager(LayoutManager manager) {
        this.manager = manager;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public boolean toggle(Value statement) {
        if (items.stream().anyMatch((i) -> i.getItem().equals(statement))) {
            ItemView found =
                    items.stream().filter((i) -> i.getItem().equals(statement)).findAny().orElseThrow(Error::new);
            // I should probably make my own exception class but the idea is that this can't happen. I suppose it's
            // not thread-safe but I don't have anything that is thread-safe.
            return remove(found);
        } else {
            add(new ItemView(statement));
            return false;
        }
    }
}
