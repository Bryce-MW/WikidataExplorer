package model.data.source;

import model.data.NotFoundException;
import model.data.source.template.Entities;
import org.jetbrains.annotations.NotNull;

public class LocalCollector extends WebCollector {

    public LocalCollector(LocalRepository repository) {
        super(repository);
        triggerLoad();
    }

    @Override
    public @NotNull Boolean triggerSave() {
        return false; // Nothing will be new so don't bother saving
    }

    @Override
    protected Entities getJson(String urlStr) throws NotFoundException {
        if (loaded != null && loaded.entities.containsKey(urlStr)) {
            return loaded;
        }
        System.out.println(urlStr); // TODO: Remove debug statement
        throw new NotFoundException(urlStr);
    }
}
