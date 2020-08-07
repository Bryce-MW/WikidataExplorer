package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.Value;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.StatementList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StatementListTest {
    private static DatumQueryService queryService;
    private StatementList statementList;

    @BeforeAll
    static void init() {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
    }

    @BeforeEach
    void setUp() throws NotFoundException {
        statementList = new StatementList(
                new Item("Q42", queryService), queryService, new ArrayList<>(10));
    }

    @Test
    void toStringArray() throws NoSuchFieldException, IllegalAccessException, NotFoundException {
        // We don't actually have statement collection in yet so we need to reflect to set one for testing purposes.
        Field field = StatementList.class.getDeclaredField("statements");
        field.setAccessible(true);
        ArrayList<Value> statements = (ArrayList<Value>) field.get(statementList);
        statements.add(new Item("Q1", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")))));
        List<StringBuilder> strings = statementList.toStringArray();
        StringBuilder result = new StringBuilder(1000);
        for (StringBuilder string : strings) {
            result.append(string).append('\n');
        }

        assertEquals("◄ Q1: universe\n", result.toString());
    }

    @Test
    void parse() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("P1");
        commands.add("R");
        assertFalse(statementList.parse(commands));
    }
}