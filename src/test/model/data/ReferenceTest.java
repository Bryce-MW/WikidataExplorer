package model.data;

import model.data.pages.Item;
import model.data.pages.Property;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceTest {
    Property property;
    Value value;
    Reference reference;

    @BeforeEach
    void testReference() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(
                new LocalRepository("wikidata.json")));
        property = new Property("P428", queryService);
        value = new Item("Q54919", queryService);
        reference = new Reference(property, value, queryService);
    }

    @Test
    void getProperty() {
        assertSame(property, reference.getProperty());
    }

    @Test
    void getValue() {
        assertSame(value, reference.getValue());
    }

    @Test
    void getTitle() {
        assertEquals("botanist author abbreviation: Virtual International Authority File", reference.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Virtual International Authority File", reference.getDescription());
    }

    @Test
    void getStatements() {
        assertNull(reference.getStatements());
    }

    @Test
    void parse() {
        assertFalse(reference.parse(new ArrayList<>())); //Always false
    }
}