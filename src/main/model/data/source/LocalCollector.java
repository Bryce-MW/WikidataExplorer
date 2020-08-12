package model.data.source;

import model.data.NotFoundException;
import model.data.source.template.Entities;

public class LocalCollector extends WebCollector {
    /*
     * Class Description:
     * This class is used when only collecting data from the local repository and not from the web is desired. I
     * ended up putting more in WebCollector than I had anticipated so I just extended that class rather than
     * rewriting it. Really, a lot of it should have gone into DatumQueryService which would have allowed this class
     * to directly extend Collector.
     */
    public LocalCollector(LocalRepository repository) {
        super(repository);
        triggerLoad();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean triggerSave() {
        return false; // Nothing will be new so don't bother saving
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    protected Entities getJson(String urlStr) throws NotFoundException {
        if (loaded != null && loaded.entities.containsKey(urlStr)) {
            return loaded;
        }
        System.out.println(urlStr); // TODO: Remove debug statement, still need to do this. Move it to a new class maybe
        throw new NotFoundException(urlStr);
    }
}
