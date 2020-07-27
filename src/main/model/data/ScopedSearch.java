package model.data;

public class ScopedSearch {
    //TODO: Implement
    private Value item; // Item that this is scoped by, null for all items. Probably needs a data service for all items.

    public ScopedSearch() {
        item = null;
    }

    public ScopedSearch(Value item) {
        this.item = item;
    }
}
