package ui;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import model.prefrences.LayoutProfileManager;
import model.prefrences.PreferenceManager;
import model.prefrences.UserProfile;
import org.jetbrains.annotations.NotNull;
import ui.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class CLInterface {
    /*
     * Class Description:
     * This is the base class of the command line interface. This class is static and therefore can not be
     * instantiated. This class sets up a number of other classes which are used in the interface and also contains
     * the main REPL loop.
     */
    private static final int WINDOW_WIDTH = 20; //TODO: wrap description text
    private static final int SEP_WIDTH = 3;
    private static final String DEFAULT_ID = "Q42";
    private static int WIDTH = 117; // Does not include prompt line
    private static int HEIGHT = 20;
    private static DatumQueryService queryService;

    private static LayoutManager layout;
    private static MenuBar menuBar;

    private static LocalRepository repository;

    private static Scanner CLInput;

    /*
     * REQUIRES: not to be called
     * MODIFIES: none
     * EFFECTS : throws and error
     */
    public CLInterface() {
        throw new Error("CLInterface contains only static methods");
    }

    /*
     * REQUIRES: args is not null
     * MODIFIES: this
     * EFFECTS : starts the CLI and sets up the objects required to do so, starts the REPL loop
     */
    public static void main(String[] args) {
        // Start point for CLI interface
        CLInput = new Scanner(System.in);

        if (!(args.length != 0 && (Arrays.asList(args).contains("IntelliJ") || Arrays.asList(args).contains("exit")))) {
            getWindowSize();
        }

        repository = new LocalRepository("wikidata.json");

        ArrayList<MenuBarItem> menuItems = new ArrayList<>(3);

        Datum startingPoint = setupStartingItem(args);
        ItemViewController viewController = new ItemViewController(new ItemView(startingPoint));

        SearchBar mainSearch = new SearchBar(new ScopedSearch(viewController, queryService));
        menuBar = new MenuBar(menuItems, mainSearch, WIDTH);
        layout = new LayoutManager(WIDTH, HEIGHT, menuBar);
        layout.setSepWidth(SEP_WIDTH);

        UserProfile profile = new UserProfile("Default", layout); //Not going to bother localizing this
        LayoutProfileManager profileLayouts = profile.getLayoutProfileManager();
        PreferenceManager preferences = profile.getPreferences();

        menuItems.add(profileLayouts);
        menuItems.add(profile);
        menuItems.add(preferences);

        layout.add(viewController);

        loop(args);
    }

    /*
     * REQUIRES: args is not null
     * MODIFIES: queryService
     * EFFECTS : sets up ther initial item and teh collector
     */
    @NotNull
    private static Datum setupStartingItem(String[] args) {
        if (Arrays.asList(args).contains("web")) {
            queryService = new DatumQueryService(new WebCollector(repository));
        } else {
            queryService = new DatumQueryService(new LocalCollector(repository));
        }

        try {
            return new Item(DEFAULT_ID, queryService);
        } catch (NotFoundException e) {
            // This should absolutely not happen unless local is selected and the repo is empty. In that case,
            // throwing an error is appropriate since it's hard to start the program working in this case
            throw new Error("Unable to start due to not finding required data.", e);
        }
    }

    /*
     * REQUIRES: none
     * MODIFIES: this
     * EFFECTS : gets the size of the console in a kind of annoying way
     */
    private static void getWindowSize() {
        //This is my attempt to get the console size. Don't complain about how confusing it is:
            /*
            "\u001b[s"             save cursor position
            "\u001b[5000;5000H"    move to col 5000 row 5000
            "\u001b[6n"            request cursor position
            "\u001b[u"             restore cursor position
            */
        Pattern last = CLInput.delimiter();
        CLInput.useDelimiter("R");
        System.out.print("\u001b[s");
        System.out.print("\u001b[5000;5000H");
        System.out.print("\u001b[6n");
        System.out.print("\u001b[u");
        String pos = CLInput.nextLine();
        CLInput.useDelimiter(last);
        // Should look like \u001b[25;80R" where 25 is row and 80 is column.
        HEIGHT = Integer.parseInt(pos.substring(2).split(";", 2)[0]) - 1;
        WIDTH = Integer.parseInt(pos.split(";", 2)[1].split("R", 2)[0]);
    }

    /*
     * REQUIRES: args is not null
     * MODIFIES: this
     * EFFECTS : loops until an exit command is found
     */
    private static void loop(String[] args) {
        layout.print();

        String command;
        System.out.print("Data $ ");
        if (args.length != 0) {
            if (Arrays.asList(args).contains("exit")) {
                StringBuilder commands = new StringBuilder();
                Arrays.asList(args).forEach((i) -> commands.append(i).append('\n'));
                commands.append("exit");
                CLInput = new Scanner(commands.toString());
            }
        }

        while (!(command = CLInput.nextLine()).equals("exit")) {
            parse(command);
            layout.print();

            System.out.print("Data $ ");
        }
    }

    /*
     * REQUIRES: command is a whitespace separated tree of commands
     * MODIFIES: this
     * EFFECTS : parses a command from the REPL
     */
    private static void parse(String command) {
        List<String> instructions = Arrays.asList(command.split("\\s"));
        boolean result;
        if (instructions.size() == 0) {
            result = false;
        } else if (instructions.get(0).length() == 1) {
            result = menuBar.parse(instructions);
        } else if (instructions.get(0).equalsIgnoreCase("save")) {
            result = queryService.triggerSave();
        } else if (instructions.get(0).equalsIgnoreCase("load")) {
            result = queryService.triggerLoad();
        } else {
            result = layout.parse(instructions);
        }
        if (!result) {
            System.out.println("Failed");
        }
    }
}
