package model.prefrences;

import model.data.DatumQueryService;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.LayoutManager;
import ui.cli.MenuBar;
import ui.cli.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileTest {
    UserProfile userProfile;

    @BeforeEach
    void setUp() {
        DatumQueryService queryService = new DatumQueryService(new WebCollector());
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
        assertEquals(1, userProfile.getLayout().getProfiles().size());
    }

    @Test
    void testToString() {
        assertEquals("P: Test", userProfile.toString());
    }
}