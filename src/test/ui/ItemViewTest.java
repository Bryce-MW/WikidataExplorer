package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemViewTest {
    private ItemView itemView;

    @BeforeEach
    void setUp() throws NotFoundException {
        itemView = new ItemView(new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")))));
    }

    @Test
    void updateLanguage() {
        assertDoesNotThrow(() -> itemView.updateLanguage("es")); // Spanish!
        // Does not do anything yet!
    }

    @Test
    void toStringArray() {
        List<StringBuilder> itemStrings = itemView.toStringArray();
        StringBuilder total = new StringBuilder(1000);
        for (StringBuilder itemString : itemStrings) {
            total.append(itemString).append('\n');
        }
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃Q42                         ┃\n" +
                "┃Douglas Adams               ┃\n" +
                "┃English writer and humorist ┃\n" +
                "┠────────────────────────────┨\n" +
                "┃S: Search                   ┃\n" +
                "┠────────────────────────────┨\n" +
                "┃◄ P18:image                 ┃\n" +
                "┃◄ P19:place of birth        ┃\n" +
                "┃◄ P20:place of death        ┃\n" +
                "┃◄ P21:sex or gender         ┃\n" +
                "┃◄ P22:father                ┃\n" +
                "┃◄ P25:mother                ┃\n" +
                "┃◄ P26:spouse                ┃\n" +
                "┃◄ P27:country of citizenship┃\n" +
                "┃◄ P31:instance of           ┃\n" +
                "┃◄ P40:child                 ┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", total.toString());
    }

    @Test
    void getItem() {
    }

    @Test
    void parse() {
    }

    @Test
    void toggleLeft() {
    }

    @Test
    void setController() {
    }
}