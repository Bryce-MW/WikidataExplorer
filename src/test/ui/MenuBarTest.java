package ui;

import model.data.DatumQueryService;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuBarTest {
    MenuBar menuBar;
    Item q42;
    DatumQueryService queryService;

    @BeforeEach
    void setUp() {
        queryService = new DatumQueryService(new WebCollector());
        q42 = new Item("Q42", queryService);
        menuBar = new MenuBar(new ArrayList<>(1),
                new SearchBar(new ScopedSearch(q42, queryService)), 100);
    }

    @Test
    void swapSearch() {
        SearchBar newSearch = new SearchBar(new ScopedSearch(q42, queryService)) {
            @Override
            public String toString() {
                return "qqqqqqqqq";
            }
        };
        StringBuilder searchLine = menuBar.toStringArray().get(1);
        assertEquals('h', searchLine.charAt(searchLine.length() - 2));

        menuBar.swapSearch(newSearch);
        searchLine = menuBar.toStringArray().get(1);
        assertEquals('q', searchLine.charAt(searchLine.length() - 2));
    }

    @Test
    void addItem() {
        StringBuilder searchLine = menuBar.toStringArray().get(1);
        assertFalse(searchLine.toString().contains("hi"));
        menuBar.addItem(new MenuBarItem() {
            @Override
            public String getCommandString() {
                return "null";
            }

            @Override
            public Boolean parse(List<String> subList) {
                return false;
            }

            @Override
            public String toString() {
                return "hi";
            }
        });
        searchLine = menuBar.toStringArray().get(1);
        assertTrue(searchLine.toString().contains("hi"));
    }

    @Test
    void removeItem() {
        MenuBarItem toAdd = new MenuBarItem() {
            @Override
            public String getCommandString() {
                return "null";
            }

            @Override
            public Boolean parse(List<String> subList) {
                return false;
            }

            @Override
            public String toString() {
                return "hi";
            }
        };
        menuBar.addItem(toAdd);
        StringBuilder searchLine = menuBar.toStringArray().get(1);
        assertTrue(searchLine.toString().contains("hi"));
        menuBar.removeItem(toAdd);
        searchLine = menuBar.toStringArray().get(1);
        assertFalse(searchLine.toString().contains("hi"));
    }

    @Test
    void toStringArray() {
        List<StringBuilder> strings = menuBar.toStringArray();
        StringBuilder result = new StringBuilder(1000);
        for (StringBuilder string : strings) {
            result.append(string).append('\n');
        }

        assertEquals(
                "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┯━" +
                        "━━━━━━━━┓\n" +
                        "┃                                                                                        ┃S: Search┃" +
                        "\n" +
                        "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┷━━━━━━━━━┛" +
                        "\n", result.toString());
    }
}