package model.prefrences;

import model.data.ScopedSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.LayoutManager;
import ui.MenuBar;
import ui.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProfileTest {
    UserProfile userProfile;

    @BeforeEach
    void setUp() {
        userProfile = new UserProfile("Test",
                new LayoutManager(10, 10,
                        new MenuBar(new ArrayList<>(), new SearchBar(new ScopedSearch(null)), 10)));
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