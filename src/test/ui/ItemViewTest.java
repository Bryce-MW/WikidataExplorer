package ui;

import model.data.DatumQueryService;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemViewTest {
    private ItemView itemView;

    @BeforeEach
    void setUp() {
        itemView = new ItemView(new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")))));
    }

    @Test
    void updateLanguage() {
        itemView.updateLanguage("es"); // Spanish!
        // Does not do anything yet!
    }

    @Test
    void toStringArray() {
        List<StringBuilder> itemStrings = itemView.toStringArray();
        StringBuilder total = new StringBuilder(1000);
        for (StringBuilder itemString : itemStrings) {
            total.append(itemString).append('\n');
        }
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃Q42                        ┃\n" +
                "┃Douglas Adams              ┃\n" +
                "┃English writer and humorist┃\n" +
                "┠───────────────────────────┨\n" +
                "┃S: Search                  ┃\n" +
                "┠───────────────────────────┨\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", total.toString());
    }
}