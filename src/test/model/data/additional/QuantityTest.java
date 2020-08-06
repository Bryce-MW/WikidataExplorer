package model.data.additional;

import model.data.DatumQueryService;
import model.data.additional.helpers.QuantityData;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuantityTest {
    private static Quantity additional;

    @BeforeAll
    static void setUp() {
        Map<String, String> quantity = new HashMap<>();
        quantity.put("amount", "+10.38");
        quantity.put("upperBound", "+10.375");
        quantity.put("lowerBound", "+10.385");
        quantity.put("unit", "http://www.wikidata.org/entity/Q712226");

        additional = new Quantity(new QuantityData(quantity),
                new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("+10.38", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("dimensionless quantity", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("+10.38", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}