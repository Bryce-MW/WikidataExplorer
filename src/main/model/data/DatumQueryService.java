package model.data;

import model.data.source.Collector;
import model.data.source.WebCollector;

public class DatumQueryService {
    //TODO: Implement
    private final Collector collector;

    public DatumQueryService(Collector collector) {
        this.collector = collector;
    }

    public ScopedSearch getScopedSearch() {
        return null;
    }
}
