package model.prefrences;

import model.data.ScopedSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.LayoutManager;
import ui.MenuBar;
import ui.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LayoutProfileManagerTest {
    LayoutProfileManager layoutProfileManager;

    @BeforeEach
    void setUp() {
        layoutProfileManager = new LayoutProfileManager(
                new LayoutProfile("Test",
                        new LayoutManager(10, 10, new MenuBar(new ArrayList<>(0),
                                new SearchBar(new ScopedSearch(null)), 10))));
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
                        new SearchBar(new ScopedSearch(null)), 10))));
        assertEquals(2, layoutProfileManager.getProfiles().size());
        assertEquals("qwerty", layoutProfileManager.getProfiles().get(1).getName());
    }

    @Test
    void testToString() {
        assertEquals("L: Test", layoutProfileManager.toString());
    }
}