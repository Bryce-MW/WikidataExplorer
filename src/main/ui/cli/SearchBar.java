package ui.cli;

import model.data.ScopedSearch;
import model.data.Value;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchBar implements MenuBarItem {
    //TODO: Implement
    private final ScopedSearch searchService;
    private final @NotNull ArrayList<Value> results;

    public SearchBar(ScopedSearch searchService) {
        this.searchService = searchService;
        results = new ArrayList<>(50);
    }

    public @NotNull List<Value> getResults() {
        return results;
    }

    public List<Value> search(String query) {
        return searchService.findElement(query);
    }

    //TODO: Rendering Stuff

    public @NotNull String toString() {
        return "S: Search";
    }

    @Override
    public @NotNull Boolean parse(@NotNull List<String> subList) {
        if (subList.size() == 0) {
            return false;
        }
        String id = subList.get(0);
        List<Value> values = search(id);
        searchService.add(values);
        return false;
    }

    @Override
    public @NotNull String getCommandString() {
        return "S";
    }
}
