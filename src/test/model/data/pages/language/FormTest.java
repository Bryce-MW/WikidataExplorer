package model.data.pages.language;

import model.data.DatumQueryService;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.Test;

class FormTest {

    @Test
    void setUp() {
        new Form("L30-F2", new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json"))));
        // Forms are weird so I have not implemented yet. Just testing for no exceptions
    }
}