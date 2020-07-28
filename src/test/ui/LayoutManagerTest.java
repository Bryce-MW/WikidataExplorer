package ui;

import model.data.DatumQueryService;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LayoutManagerTest {
    private LayoutManager layoutManager;

    @BeforeEach
    void setUp() {
        layoutManager = new LayoutManager(100, 100, new MenuBar(
                new ArrayList<>(), new SearchBar(new ScopedSearch()), 100));
    }

    @Test
    void add() {
        layoutManager.add(new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector())))));
        assertFalse(layoutManager.isEmpty());
        assertEquals(1, layoutManager.size());
    }

    @Test
    void remove() {
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector()))));
        layoutManager.add(itemViewController);
        assertFalse(layoutManager.isEmpty());
        assertEquals(1, layoutManager.size());

        layoutManager.remove(itemViewController);
        assertTrue(layoutManager.isEmpty());
        assertEquals(0, layoutManager.size());
    }

    @Test
    void isEmpty() {
        assertTrue(layoutManager.isEmpty());
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector()))));
        layoutManager.add(itemViewController);
        assertFalse(layoutManager.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, layoutManager.size());
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector()))));
        layoutManager.add(itemViewController);
        assertEquals(1, layoutManager.size());
    }

    @Test
    void setSepWidth() {
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector()))));
        layoutManager.add(itemViewController);
        layoutManager.setSepWidth(1);
        ArrayList<StringBuilder> output0 = layoutManager.toStringArray();

        assertEquals(30, output0.get(output0.size() - 1).length());
        layoutManager.setSepWidth(20);
        output0 = layoutManager.toStringArray();
        assertEquals(49, output0.get(output0.size() - 1).length());
        layoutManager.setSepWidth(30);
        output0 = layoutManager.toStringArray();
        assertEquals(59, output0.get(output0.size() - 1).length());
    }

    @Test
    void print() {
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new WebCollector()))));
        layoutManager.add(itemViewController);
        layoutManager.print();
        // Just prints something so all that we can check is that there are no exceptions
    }
}