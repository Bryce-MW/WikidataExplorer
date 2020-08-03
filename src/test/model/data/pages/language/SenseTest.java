package model.data.pages.language;

import model.data.DatumQueryService;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.Test;

class SenseTest {

    @Test
    void setUp() {
        new Sense("Q42", new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json"))));
        // Senses are weird so I have not implemented yet. Just testing for no exceptions
    }
}