package model.data.pages.language;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.Test;

class FormTest {

    @Test
    void setUp() throws NotFoundException {
        new Form("L30-F2", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
        // Forms are weird so I have not implemented yet. Just testing for no exceptions
    }
}