package model.data.pages.language;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.Test;

class LexemeTest {

    @Test
    void setUp() throws NotFoundException {
        new Lexeme("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
        // Lexemes are weird so I have not implemented yet. Just testing for no exceptions
    }
}