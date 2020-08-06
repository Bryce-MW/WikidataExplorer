package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MathematicalExpressionTest {
    private static MathematicalExpression additional;

    @BeforeAll
    static void setUp() {
        additional =
                new MathematicalExpression("\\frac{1}{2}",
                        new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("\\frac{1}{2}", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("\\frac{1}{2}", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("\\frac{1}{2}", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}