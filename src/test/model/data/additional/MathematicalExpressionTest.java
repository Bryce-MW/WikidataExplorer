package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class MathematicalExpressionTest {
    private static MathematicalExpression additional;

    @BeforeAll
    static void setUp() {
        additional = new MathematicalExpression(new DatumQueryService(new WebCollector()));
    }

    @Test
    void getTitle() {
        assertNull(additional.getTitle());
    }

    @Test
    void getDescription() {
        assertNull(additional.getDescription());
    }

    @Test
    void getID() {
        assertNull(additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}