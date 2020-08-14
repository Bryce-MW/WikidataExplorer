package model.data;

import model.data.pages.Item;
import org.jetbrains.annotations.NotNull;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.util.ArrayList;
import java.util.List;

public class ScopedSearch {
    /*
     * Class Description:
     * This is the class which actually does the searching for the top level item or for a property within an item. I
     *  really should have split this into two classes since this just ends up doing some annoying checks for null.
     */
    private final DatumQueryService queryService;
    private Value item; // Item that this is scoped by, null for all items. Probably needs a data service for all
    private ItemViewController controller = null;

    /*
     * REQUIRES: queryService is not null
     * MODIFIES: this
     * EFFECTS : created a new scoped search
     */
    public ScopedSearch(Value item, DatumQueryService queryService) {
        this.queryService = queryService;
        this.item = item;
    }

    /*
     * REQUIRES: queryService is not null
     * MODIFIES: this
     * EFFECTS : creates a new scoped search but for no specific item
     */
    public ScopedSearch(ItemViewController controller, DatumQueryService queryService) {
        this.controller = controller;
        this.queryService = queryService;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the item (if there is one) that this search is targeting
     */
    public Value getItem() {
        return item;
    }

    /*
     * REQUIRES: values is not null
     * MODIFIES: this
     * EFFECTS : adds a list of values which were found
     */
    public void add(List<Value> values) {
        if (controller != null) {
            for (Value value : values) {
                controller.add(new ItemView(value));
            }
        } else if (item != null) {
            for (Value value : values) {
                item.addStatement(value);
            }
        }
    }

    /*
     * REQUIRES: query is not null and is a valid search query for the item or for an ID overall if no item
     * MODIFIES: this
     * EFFECTS : finds the specific element specified by the query
     */
    public List<Value> findElement(String query) {
        if (controller != null) {
            ArrayList<Value> values = new ArrayList<>(1);
            try {
                values.add(new Item(query, queryService));
            } catch (NotFoundException e) {
                // Search was not found so we just return the empty list;
            }
            return values;
        }
        if (item != null) {
            return itemIsNull(query);
        }
        ArrayList<Value> values = new ArrayList<>(1);
        try {
            values.add(new Item(query, queryService));
        } catch (NotFoundException e) {
            // Search was not found so we just return the empty list;
        }
        return values;
    }

    /*
     * REQUIRES: query is not null
     * MODIFIES: this
     * EFFECTS : does some specific actions if the item is null
     */
    @NotNull
    private ArrayList<Value> itemIsNull(String query) {
        ArrayList<String> tree = new ArrayList<>(2);
        tree.add(item.getID());
        tree.add(query);
        ArrayList<Value> result = new ArrayList<>(1);
        if (item instanceof Datum) { // TODO: This is really really bad! Should be delegated to Value implementation
            try {
                result.add(queryService.getStatementByTree(tree, (Datum) item));
            } catch (NotFoundException ignored) {
                // Very odd but I guess we just don't need to return anything?
            }
        }
        return result;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the controller (if there is one) that this search is bound to
     */
    public ItemViewController getController() {
        return controller;
    }
}
