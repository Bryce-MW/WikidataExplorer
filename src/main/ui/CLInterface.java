package ui;

import model.data.Datum;
import model.data.DatumQueryService;
import model.data.pages.Item;
import model.data.source.WebCollector;
import model.prefrences.LayoutProfileManager;
import model.prefrences.PreferenceManager;
import model.prefrences.UserProfile;

import java.util.ArrayList;
import java.util.Scanner;

public final class CLInterface {
    private static final int WIDTH = 176; // Does not include prompt line
    private static final int HEIGHT = 70;
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

    private static final Scanner CLInput = new Scanner(System.in);

    public CLInterface() {
        throw new Error("CLInterface contains only static methods");
    }

    public static void main(String[] args) {
        // Start point for CLI interface
        profile = new UserProfile("Default", layout); //Not going to bother localizing this
        profileLayouts = profile.getLayout();
        preferences = profile.getPreferences();

        ArrayList<MenuBarItem> menuItems = new ArrayList<>(3);
        menuItems.add(profileLayouts);
        menuItems.add(profile);
        menuItems.add(preferences);

        queryService = new DatumQueryService(new WebCollector());

        mainSearch = new SearchBar(queryService.getScopedSearch(null));
        menuBar = new MenuBar(menuItems, mainSearch, WIDTH);
        layout = new LayoutManager(117, 70, menuBar);
        layout.setDefaultWidth(20);
        layout.setSepWidth(3);

        Datum startingPoint = new Item("Q42", queryService);

        layout.add(new ItemViewController(new ItemView(startingPoint)));

        layout.print();

        String command;
        System.out.print("Data $ ");
        if (args.length == 0) {
            while (!(command = CLInput.nextLine()).equals("exit")) {
                parse(command);
                layout.print();

                System.out.print("Data $ ");
            }
        }
    }

    private static void parse(String command) {
        // TODO: Implement
        switch (command) {
            default:
                break;
        }
    }
}
