package ui;

import model.data.ScopedSearch;
import model.data.Value;

import java.util.ArrayList;
import java.util.List;

public class SearchBar implements MenuBarItem {
    //TODO: Implement
    private ScopedSearch searchService;
    private ArrayList<Value> results;

    public SearchBar(ScopedSearch searchService) {
        this.searchService = searchService;
        results = new ArrayList<>(50);
    }

    public List<Value> getResults() {
        return results;
    }

    public List<Value> search(String query) {
        return null;
    }

    //TODO: Rendering Stuff
}
