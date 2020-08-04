package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemViewControllerTest {
    private ItemViewController itemViewController;
    private DatumQueryService queryService;
    private ItemView itemView;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        Item q42 = new Item("Q42", queryService);
        itemView = new ItemView(q42);
        itemViewController = new ItemViewController(itemView);
    }

    @Test
    void add() throws NotFoundException {
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
    void size() throws NotFoundException {
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
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃Q42                         ┃\n" +
                "┃Douglas Adams               ┃\n" +
                "┃English writer and humorist ┃\n" +
                "┠────────────────────────────┨\n" +
                "┃S: Search                   ┃\n" +
                "┠────────────────────────────┨\n" +
                "┃◄ P18:image                 ┃\n" +
                "┃◄ P19:place of birth        ┃\n" +
                "┃◄ P20:place of death        ┃\n" +
                "┃◄ P21:sex or gender         ┃\n" +
                "┃◄ P22:father                ┃\n" +
                "┃◄ P25:mother                ┃\n" +
                "┃◄ P26:spouse                ┃\n" +
                "┃◄ P27:country of citizenship┃\n" +
                "┃◄ P31:instance of           ┃\n" +
                "┃◄ P40:child                 ┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", string.toString());
    }
}