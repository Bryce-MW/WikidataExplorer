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
import ui.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class CLInterface {
    private static int WIDTH = 117; // Does not include prompt line
    private static int HEIGHT = 20;
    private static final int WINDOW_WIDTH = 20;
    private static final int SEP_WIDTH = 3;
    private static final String DEFAULT_ID = "Q42";

    private static DatumQueryService queryService;

    private static LayoutManager layout;
    private static MenuBar menuBar;
    private static SearchBar mainSearch;

    private static UserProfile profile;
    private static LayoutProfileManager profileLayouts;
    private static PreferenceManager preferences;

    private static LocalRepository repository;

    private static Scanner CLInput;

    public CLInterface() {
        throw new Error("CLInterface contains only static methods");
    }

    public static void main(String[] args) {
        // Start point for CLI interface
        CLInput = new Scanner(System.in);

        if (!(args.length != 0 && (args[0].equals("Intellij") || args[0].equals("exit")))) {
            getWindowSize();
        }

        repository = new LocalRepository("wikidata.json");

        ArrayList<MenuBarItem> menuItems = new ArrayList<>(3);

        if (Arrays.asList(args).contains("web")) {
            queryService = new DatumQueryService(new WebCollector(repository));
        } else {
            queryService = new DatumQueryService(new LocalCollector(repository));
        }

        Datum startingPoint = null;
        try {
            startingPoint = new Item("Q42", queryService);
        } catch (NotFoundException e) {
            // This should absolutely not happen unless local is selected and the repo is empty. In that case,
            // throwing an error is appropriate since it's hard to start the program working in this case
            throw new Error("Unable to start due to not finding required data.", e);
        }
        ItemViewController viewController = new ItemViewController(new ItemView(startingPoint));

        mainSearch = new SearchBar(new ScopedSearch(viewController, queryService));
        menuBar = new MenuBar(menuItems, mainSearch, WIDTH);
        layout = new LayoutManager(WIDTH, HEIGHT, menuBar);
        layout.setSepWidth(SEP_WIDTH);

        profile = new UserProfile("Default", layout); //Not going to bother localizing this
        profileLayouts = profile.getLayout();
        preferences = profile.getPreferences();

        menuItems.add(profileLayouts);
        menuItems.add(profile);
        menuItems.add(preferences);

        layout.add(viewController);

        loop(args);
    }

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

    private static void loop(String[] args) {
        layout.print();

        String command;
        System.out.print("Data $ ");
        if (args.length != 0) {
            if (args[0].equals("exit")) {
                CLInput = new Scanner("test\nexit");
            }
        }

        while (!(command = CLInput.nextLine()).equals("exit")) {
            parse(command);
            layout.print();

            System.out.print("Data $ ");
        }
    }

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
