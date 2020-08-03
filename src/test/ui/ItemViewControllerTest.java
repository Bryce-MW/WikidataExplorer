package ui;

import model.data.DatumQueryService;
import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemViewControllerTest {
    private ItemViewController itemViewController;
    private DatumQueryService queryService;
    private Item q42;
    private ItemView itemView;

    @BeforeEach
    void setUp() {
        queryService = new DatumQueryService(new WebCollector());
        q42 = new Item("Q42", queryService);
        itemView = new ItemView(q42);
        itemViewController = new ItemViewController(itemView);
    }

    @Test
    void add() {
        itemViewController.add(new ItemView(
                new Item("P1559", queryService)));
        assertEquals(2, itemViewController.size());
    }

    @Test
    void remove() {
        itemViewController.remove(itemView);
        assertTrue(itemViewController.isEmpty());
    }

    @Test
    void isEmpty() {
        assertFalse(itemViewController.isEmpty());
        itemViewController.remove(itemView);
        assertTrue(itemViewController.isEmpty());
    }

    @Test
    void size() {
        assertEquals(1, itemViewController.size());
        itemViewController.remove(itemView);
        assertEquals(0, itemViewController.size());
        itemViewController.add(itemView);
        itemViewController.add(new ItemView(
                new Item("P1559", queryService)));
        assertEquals(2, itemViewController.size());
    }

    @Test
    void toStringArray() {
        List<StringBuilder> strings = itemViewController.toStringArray();
        StringBuilder string = new StringBuilder(1000);
        for (StringBuilder itemString : strings) {
            string.append(itemString).append('\n');
        }
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃Q42                        ┃\n" +
                "┃Douglas Adams              ┃\n" +
                "┃English writer and humorist┃\n" +
                "┠───────────────────────────┨\n" +
                "┃S: Search                  ┃\n" +
                "┠───────────────────────────┨\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", string.toString());
    }
}