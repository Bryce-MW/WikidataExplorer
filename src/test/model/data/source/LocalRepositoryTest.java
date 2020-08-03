package model.data.source;

import org.junit.jupiter.api.Test;

class LocalRepositoryTest {

    @Test
    void localRepository() {
        new LocalRepository("wikidata.json");
        // Not implemented so just checking for exceptions
    }
}