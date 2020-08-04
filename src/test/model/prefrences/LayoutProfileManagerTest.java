package model.prefrences;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.LayoutManager;
import ui.cli.MenuBar;
import ui.cli.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LayoutProfileManagerTest {
    LayoutProfileManager layoutProfileManager;
    DatumQueryService queryService;
    Item q42;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        q42 = new Item("Q42", queryService);
        layoutProfileManager = new LayoutProfileManager(
                new LayoutProfile("Test",
                        new LayoutManager(10, 10, new MenuBar(new ArrayList<>(0),
                                new SearchBar(new ScopedSearch(q42, queryService)),
                                10))));
    }

    @Test
    void getProfiles() {
        assertEquals(1, layoutProfileManager.getProfiles().size());
        assertEquals("Test", layoutProfileManager.getProfiles().get(0).getName());
    }

    @Test
    void addProfile() {
        layoutProfileManager.addProfile(new LayoutProfile("qwerty",
                new LayoutManager(10, 10, new MenuBar(new ArrayList<>(0),
                        new SearchBar(new ScopedSearch(q42, queryService)), 10))));
        assertEquals(2, layoutProfileManager.getProfiles().size());
        assertEquals("qwerty", layoutProfileManager.getProfiles().get(1).getName());
    }

    @Test
    void testToString() {
        assertEquals("L: Test", layoutProfileManager.toString());
    }
}