package model.data;

import model.data.pages.Item;
import model.data.pages.Property;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.Test;

class ReferenceTest {

    @Test
    void testReference() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(
                new LocalRepository("wikidata.json")));
        new Reference(new Property("P428", queryService), new Item("Q54919", queryService), queryService);
        // Nothing should happen
    }
}