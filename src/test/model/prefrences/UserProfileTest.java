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
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserProfileTest {
    UserProfile userProfile;

    @BeforeEach
    void setUp() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        userProfile = new UserProfile("Test",
                new LayoutManager(10, 10,
                        new MenuBar(new ArrayList<>(),
                                new SearchBar(new ScopedSearch(new Item("Q42", queryService), queryService)),
                                10)));
    }

    @Test
    void getPreferences() {
        assertEquals(0, userProfile.getPreferences().getPreferences().size());
    }

    @Test
    void getLayout() {
        assertEquals(1, userProfile.getLayoutProfileManager().getProfiles().size());
    }

    @Test
    void testToString() {
        assertEquals("P: Test", userProfile.toString());
    }

    @Test
    void parse() {
        assertTrue(userProfile.parse(new ArrayList<>())); //Always true for any input
    }
}