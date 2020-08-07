package model.data;

import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StatementTest {
    private static DatumQueryService queryService;
    private static Item q42;
    private Statement statement;

    @BeforeAll
    static void init() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        q42 = new Item("Q42", queryService);
    }

    @BeforeEach
    void setUp() throws NotFoundException {
        statement = new Statement(q42, "P735", queryService);
    }

    @Test
    void getTitle() {
        assertEquals("given name", statement.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("", statement.getDescription());
    }

    @Test
    void getID() {
        assertEquals("P735", statement.getID());
    }

    @Test
    void getStatements() {
        assertEquals(2, statement.getStatements().toStringArray().size());
    }

    @Test
    void getParentID() {
        assertEquals("Q42", statement.getParentID());
    }

    @Test
    void parse() {
        ArrayList<String> command = new ArrayList<>();
        command.add("P800");
        statement.getStatements();
        assertFalse(statement.parse(command));
    }
}