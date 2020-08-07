package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.Statement;
import model.data.Value;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemViewTest {
    private ItemView itemView;
    private Item q42;
    private DatumQueryService queryService;

    @BeforeEach
    void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        q42 = new Item("Q42", queryService);
        itemView = new ItemView(q42);
    }

    @Test
    void updateLanguage() {
        assertDoesNotThrow(() -> itemView.updateLanguage("es")); // Spanish!
        // Does not do anything yet!
    }

    @Test
    void toStringArray() throws NotFoundException {
        List<StringBuilder> itemStrings = itemView.toStringArray();
        StringBuilder total = new StringBuilder(1000);
        for (StringBuilder itemString : itemStrings) {
            total.append(itemString).append('\n');
        }
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃Q42                          ┃\n" +
                "┃Douglas Adams                ┃\n" +
                "┃English writer and humorist  ┃\n" +
                "┠─────────────────────────────┨\n" +
                "┃S: Search                    ┃\n" +
                "┠─────────────────────────────┨\n" +
                "┃◄ P18: image                 ┃\n" +
                "┃◄ P19: place of birth        ┃\n" +
                "┃◄ P20: place of death        ┃\n" +
                "┃◄ P21: sex or gender         ┃\n" +
                "┃◄ P22: father                ┃\n" +
                "┃◄ P25: mother                ┃\n" +
                "┃◄ P26: spouse                ┃\n" +
                "┃◄ P27: country of citizenship┃\n" +
                "┃◄ P31: instance of           ┃\n" +
                "┃◄ P40: child                 ┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", total.toString());

        itemStrings = new ItemView(new Statement(q42, "P800", queryService)).toStringArray();
        total = new StringBuilder(1000);
        for (StringBuilder itemString : itemStrings) {
            total.append(itemString).append('\n');
        }
        assertEquals("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
                "┃P800                                                      ►┃\n" +
                "┃notable work                                               ┃\n" +
                "┃                                                           ┃\n" +
                "┠───────────────────────────────────────────────────────────┨\n" +
                "┃◄ Q25169:    The Hitchhiker's Guide to the Galaxy pentalogy┃\n" +
                "┃◄ Q20736364: Dirk Gently tetralogy                         ┃\n" +
                "┃◄ Q7758404:  The Private Life of Genghis Khan              ┃\n" +
                "┃◄ Q578895:   The Restaurant at the End of the Universe     ┃\n" +
                "┃◄ Q721:      Life, the Universe and Everything             ┃\n" +
                "┃◄ Q1042294:  So Long, and Thanks for All the Fish          ┃\n" +
                "┃◄ Q187655:   Mostly Harmless                               ┃\n" +
                "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n", total.toString());
    }

    @Test
    void getItem() {
        assertSame(q42, itemView.getItem());
    }

    @Test
    void parse() throws NotFoundException, NoSuchFieldException, IllegalAccessException {
        assertFalse(itemView.parse(new ArrayList<>()));
        ItemView other = new ItemView(new Statement(q42, "P800", queryService));
        new ItemViewController(other);
        assertFalse(other.parse(new ArrayList<>()));

        Field value = Value.class.getDeclaredField("id");
        value.setAccessible(true);

        Field mod = Field.class.getDeclaredField("modifiers");
        mod.setAccessible(true);
        mod.setInt(value, value.getModifiers() & ~Modifier.FINAL);

        value.set(other.getItem(), "O");
        // This reflection is a big mess!

        assertFalse(other.parse(new ArrayList<>()));

        ArrayList<String> commands = new ArrayList<>();
        commands.add("P31");
        assertFalse(itemView.parse(commands));
        commands.add(0, "S");
        assertFalse(itemView.parse(commands));
    }

    @Test
    void toggleLeft() {
        new ItemViewController(itemView);
        ArrayList<String> commands = new ArrayList<>();
        commands.add("P31");
        assertFalse(itemView.parse(commands));
    }
}