package ui;

import model.data.DatumQueryService;
import model.prefrences.PreferenceManager;
import model.prefrences.UserProfile;

public final class CLInterface {
    //TODO: Implement
    private static DatumQueryService queryService;

    private static LayoutManager layout;
    private static MenuBar menuBar;
    private static SearchBar mainSearch;

    private static UserProfile profile;
    private static PreferenceManager preferences;

    public CLInterface() {
        throw new Error("CLInterface contains only static methods");
    }

    public static void main(String[] args) {
        // Start point for CLI interface
    }
}
