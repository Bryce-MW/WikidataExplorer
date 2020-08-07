package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LayoutManagerTest {
    DatumQueryService queryService;
    private LayoutManager layoutManager;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        layoutManager = new LayoutManager(100, 100, new MenuBar(
                new ArrayList<>(), new SearchBar(new ScopedSearch(new Item("Q42", queryService), queryService)),
                100));
    }

    @Test
    void add() throws NotFoundException {
        layoutManager.add(new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")))))));
        assertFalse(layoutManager.isEmpty());
        assertEquals(1, layoutManager.size());
    }

    @Test
    void remove() throws NotFoundException {
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))))));
        layoutManager.add(itemViewController);
        assertFalse(layoutManager.isEmpty());
        assertEquals(1, layoutManager.size());

        layoutManager.remove(itemViewController);
        assertTrue(layoutManager.isEmpty());
        assertEquals(0, layoutManager.size());
    }

    @Test
    void isEmpty() throws NotFoundException {
        assertTrue(layoutManager.isEmpty());
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))))));
        layoutManager.add(itemViewController);
        assertFalse(layoutManager.isEmpty());
    }

    @Test
    void size() throws NotFoundException {
        assertEquals(0, layoutManager.size());
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))))));
        layoutManager.add(itemViewController);
        assertEquals(1, layoutManager.size());
    }

    @Test
    void setSepWidth() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NotFoundException {
        Method toStringArray = LayoutManager.class.getDeclaredMethod("toStringArray");
        toStringArray.setAccessible(true);

        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))))));
        layoutManager.add(itemViewController);
        layoutManager.setSepWidth(1);
        ArrayList<StringBuilder> output0 = (ArrayList<StringBuilder>) toStringArray.invoke(layoutManager);

        assertEquals(32, output0.get(output0.size() - 1).length());
        layoutManager.setSepWidth(20);
        output0 = (ArrayList<StringBuilder>) toStringArray.invoke(layoutManager);
        assertEquals(51, output0.get(output0.size() - 1).length());
        layoutManager.setSepWidth(30);
        output0 = (ArrayList<StringBuilder>) toStringArray.invoke(layoutManager);
        assertEquals(61, output0.get(output0.size() - 1).length());
    }

    @Test
    void print() throws NotFoundException {
        ItemViewController itemViewController = new ItemViewController(new ItemView(
                new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))))));
        layoutManager.add(itemViewController);
        layoutManager.print();
        // Just prints something so all that we can check is that there are no exceptions
    }

    @Test
    void toggleLeft() throws NotFoundException {
        Item q42 = new Item("Q42", queryService);
        ItemViewController controller = new ItemViewController(new ItemView(q42));
        assertFalse(layoutManager.toggleLeft(q42, controller));
        layoutManager.add(controller);
        assertTrue(layoutManager.toggleLeft(q42, controller));

        Item p800 = new Item("P800", queryService);
        ItemViewController p800ViewController = new ItemViewController(new ItemView(p800));
        layoutManager.add(p800ViewController);
        assertTrue(layoutManager.toggleLeft(p800, p800ViewController));
        assertTrue(layoutManager.toggleLeft(q42, controller));
    }

    @Test
    void toggleRight() throws NotFoundException {
        Item q42 = new Item("Q42", queryService);
        ItemViewController controller = new ItemViewController(new ItemView(q42));
        assertFalse(layoutManager.toggleRight(q42, controller));
        layoutManager.add(controller);
        assertTrue(layoutManager.toggleRight(q42, controller));

        Item p800 = new Item("P800", queryService);
        ItemViewController p800ViewController = new ItemViewController(new ItemView(p800));
        layoutManager.add(p800ViewController);
        assertTrue(layoutManager.toggleRight(p800, p800ViewController));
        assertTrue(layoutManager.toggleRight(q42, controller));
    }
}