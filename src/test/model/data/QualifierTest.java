package model.data;

import model.data.additional.LiteralString;
import model.data.pages.Property;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QualifierTest {
    private static DatumQueryService queryService;
    private static Property property;
    private static LiteralString value;
    private Qualifier qualifier;

    @BeforeAll
    static void init() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        property = new Property("P1545", queryService);
        value = new LiteralString("1", queryService);
    }

    @BeforeEach
    void setUp() {
        qualifier = new Qualifier(property, value, queryService);
    }

    @Test
    void getProperty() {
        assertEquals(property, qualifier.getProperty());
    }

    @Test
    void getValue() {
        assertEquals(value, qualifier.getValue());
    }

    @Test
    void getTitle() {
        assertEquals("series ordinal: 1", qualifier.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("1", qualifier.getDescription());
    }

    @Test
    void getStatements() {
        assertNull(qualifier.getStatements());
    }

    @Test
    void parse() {
        assertFalse(qualifier.parse(new ArrayList<>())); //Always false
    }
}