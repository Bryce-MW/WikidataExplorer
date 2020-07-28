package ui;

import model.data.ScopedSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SearchBarTest {
    private SearchBar searchBar;

    @BeforeEach
    void setUp() {
        searchBar = new SearchBar(new ScopedSearch());
    }

    @Test
    void getResults() {
        assertEquals(0, searchBar.getResults().size());
        // Currently always empty, searching not supported yet!
    }

    @Test
    void search() {
        assertNull(searchBar.search("Anything"));
        // Currently always null, searching not supported yet!
    }

    @Test
    void testToString() {
        assertEquals("S: Search", searchBar.toString());
    }
}