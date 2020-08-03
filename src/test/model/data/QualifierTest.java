package model.data;

import model.data.additional.LiteralString;
import model.data.pages.Property;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QualifierTest {
    private static DatumQueryService queryService;
    private static Property property;
    private static LiteralString value;
    private Qualifier qualifier;

    @BeforeAll
    static void init() {
        queryService = new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json")));
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
}