package model.data;

public class ScopedSearch {
    //TODO: Implement
    private final Value item; // Item that this is scoped by, null for all items. Probably needs a data service for all
    // items.
    // private DatumQueryService queryService; TODO: Not needed yet

    public ScopedSearch() {
        //this.queryService = queryService;
        item = null;
    }

    public ScopedSearch(Value item) {
        //this.queryService = queryService;
        this.item = item;
    }

    public Value getItem() {
        return item;
    }
}
