package ui;

import model.data.DatumQueryService;
import model.data.Value;
import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatementListTest {
    private StatementList statementList;
    private static DatumQueryService queryService;

    @BeforeAll
    static void init() {
        queryService = new DatumQueryService(new WebCollector());
    }

    @BeforeEach
    void setUp() {
        statementList = new StatementList(
                new Item("Q42", queryService), queryService);
    }

    @Test
    void toStringArray() throws NoSuchFieldException, IllegalAccessException {
        // We don't actually have statement collection in yet so we need to reflect to set one for testing purposes.
        Field field = StatementList.class.getDeclaredField("statements");
        field.setAccessible(true);
        ArrayList<Value> statements = (ArrayList<Value>) field.get(statementList);
        statements.add(new Item("Q1", new DatumQueryService(new WebCollector())));
        List<StringBuilder> strings = statementList.toStringArray();
        StringBuilder result = new StringBuilder(1000);
        for (StringBuilder string : strings) {
            result.append(string).append('\n');
        }

        assertEquals("◄ Q1:universe\n", result.toString());
    }
}