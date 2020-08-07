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

class FormTest {

    private Form form;
    private DatumQueryService queryService;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        form = new Form("L30-F2", queryService);
        // Forms are weird so I have not implemented yet. Just testing for no exceptions
    }

    @Test
    void getTitle() {
        assertEquals("founds", form.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("", form.getDescription());
    }

    @Test
    void alternateConstruct() {
        Map<String, Object> value = new HashMap<>();
        value.put("entity-type", "item");
        value.put("id", "Q30");
        value.put("numeric-id", 30D);
        EntityData data = new EntityData(value);
        assertThrows(NotFoundException.class, () -> new Form(data, queryService));
    }
}