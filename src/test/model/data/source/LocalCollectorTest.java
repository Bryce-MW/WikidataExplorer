package model.data.source;

import org.junit.jupiter.api.Test;

class LocalCollectorTest {

    @Test
    void localCollector() {
        new LocalCollector(new LocalRepository("wikidata.json"));
        // Not implemented so just checking for exceptions
    }
}