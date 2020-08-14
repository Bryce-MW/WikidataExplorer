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
     * REQUIRES: initial is not null
     * MODIFIES: this
     * EFFECTS : creates a new controller
     */
    public ItemViewController(ItemView initial) {
        items = new ArrayList<>(10);
        items.add(initial);
        initial.setController(this);
    }

    /*
     * REQUIRES: newView is not null and is not a member of this ItemViewController
     * MODIFIES: this
     * EFFECTS : adds an item view to this controller
     */
    public void add(ItemView newView) {
        items.add(newView);
        newView.setController(this);
    }

    /*
     * REQUIRES: toRemove is not null and is a member of this ItemViewController
     * MODIFIES: this
     * EFFECTS : removes a view from this controller
     */
    public boolean remove(ItemView toRemove) { // true if ViewController is now empty
        items.remove(toRemove);
        return items.isEmpty();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns true if there are no views in this controller
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the number of views
     */
    public int size() {
        return items.size();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the CLI representation of the controller
     */
    public List<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(10 * items.size());
        items.stream().map(ItemView::toStringArray).forEach(result::addAll);
        return result;
    }

    /*
     * REQUIRES: instructions is not null and contains at least one element
     * MODIFIES: this
     * EFFECTS : parses a REPL command directed at this controller
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
     * REQUIRES: statement is not null
     * MODIFIES: this
     * EFFECTS : toggles statement on the controller to the left
     */
    public boolean toggleLeft(Value statement) {
        if (manager == null) {
            return false;
        }
        return manager.toggleLeft(statement, this);
    }

    /*
     * REQUIRES: statement is not null
     * MODIFIES: this
     * EFFECTS : toggles statement on the controller to the right
     */
    public boolean toggleRight(Value statement) {
        if (manager == null) {
            return false;
        }
        return manager.toggleRight(statement, this);
    }

    /*
     * REQUIRES: manager is not null
     * MODIFIES: this
     * EFFECTS : sets the manager containing this controller
     */
    public void setManager(LayoutManager manager) {
        this.manager = manager;
    }

    /*
     * REQUIRES: statement is not null
     * MODIFIES: this
     * EFFECTS : toggles the statement on this controller
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
