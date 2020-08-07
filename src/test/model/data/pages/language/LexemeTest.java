package model.data.pages.language;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LexemeTest {

    private Lexeme q42;
    private DatumQueryService queryService;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        q42 = new Lexeme("Q42", queryService);
        // Lexemes are weird so I have not implemented yet. Just testing for no exceptions
    }

    @Test
    void getTitle() {
        assertEquals("Douglas Adams", q42.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("English writer and humorist", q42.getDescription());

    }

    @Test
    void alternateConstruct() {
        Map<String, Object> value = new HashMap<>();
        value.put("entity-type", "item");
        value.put("id", "Q30");
        value.put("numeric-id", 30D);
        EntityData data = new EntityData(value);
        assertThrows(NotFoundException.class, () -> new Lexeme(data, queryService));
    }
}