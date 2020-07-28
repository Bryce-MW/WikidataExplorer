package model.prefrences;

import model.data.ScopedSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.LayoutManager;
import ui.MenuBar;
import ui.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LayoutProfileTest {
    LayoutProfile layoutProfile;
    LayoutManager layoutManager;

    @BeforeEach
    void setUp() {
        layoutManager = new LayoutManager(100, 100, new MenuBar(new ArrayList<>(0),
                new SearchBar(new ScopedSearch(null)), 10));
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