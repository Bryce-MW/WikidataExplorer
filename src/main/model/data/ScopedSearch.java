package model.data;

import model.data.pages.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.util.ArrayList;
import java.util.List;

public class ScopedSearch {
    // items.
    private final DatumQueryService queryService;
    //TODO: Implement
    private Value item; // Item that this is scoped by, null for all items. Probably needs a data service for all
    private @Nullable ItemViewController controller = null;

    public ScopedSearch(Value item, DatumQueryService queryService) {
        this.queryService = queryService;
        this.item = item;
    }

    public ScopedSearch(ItemViewController controller, DatumQueryService queryService) {
        this.controller = controller;
        this.queryService = queryService;
    }

    public Value getItem() {
        return item;
    }

    public void add(@NotNull List<Value> values) {
        if (controller != null) {
            for (Value value : values) {
                controller.add(new ItemView(value));
            }
        } else {
            for (Value value : values) {
                item.addStatement(value);
            }
        }
    }

    public @NotNull List<Value> findElement(String query) {
        if (controller != null) {
            ArrayList<Value> values = new ArrayList<>(1);
            try {
                values.add(new Item(query, queryService));
            } catch (NotFoundException e) {
                // Search was not found so we just return the empty list;
            }
            return values;
        }
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
}
