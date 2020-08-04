package model.data.source;

import model.data.source.template.Entities;

public class LocalCollector extends WebCollector {

    public LocalCollector(LocalRepository repository) {
        super(repository);
        triggerLoad();
    }

    @Override
    public Boolean triggerSave() {
        return false; // Nothing will be new so don't bother saving
    }

    @Override
    public Boolean triggerLoad() {
        return super.triggerLoad();
    }

    @Override
    protected Entities getJson(String urlStr) {
        if (loaded != null && loaded.entities.containsKey(urlStr)) {
            return loaded;
        }
        System.out.println(urlStr); // TODO: Remove debug statement
        return new Entities(); // Will always cause a crash, I should do some Exception stuff
    }
}
