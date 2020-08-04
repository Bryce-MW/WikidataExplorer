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

class LayoutProfileTest {
    LayoutProfile layoutProfile;
    LayoutManager layoutManager;

    @BeforeEach
    void setUp() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        layoutManager = new LayoutManager(100, 100, new MenuBar(new ArrayList<>(0),
                new SearchBar(new ScopedSearch(new Item("Q42", queryService), queryService)), 10));
        layoutProfile = new LayoutProfile("Test", layoutManager);
    }

    @Test
    void getLayout() {
        assertEquals(layoutManager, layoutProfile.getLayout());
    }

    @Test
    void getName() {
        assertEquals("Test", layoutProfile.getName());
    }

    @Test
    void setName() {
        layoutProfile.setName("qwerty");
        assertEquals("qwerty", layoutProfile.getName());
    }
}