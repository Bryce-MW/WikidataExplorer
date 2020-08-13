package model.prefrences;

import ui.cli.LayoutManager;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends MenuBarItem {
    /*
     * Class Description:
     * I never ended up implementing this class but the idea what that a user profile would contain a number of
     * layouts as well as user preferences which I also did not implement.
     */
    private final PreferenceManager preferences;
    private final LayoutProfileManager layout;

    private final String name;

    /*
     * REQUIRES: name is not null, defaultLayout is not null
     * MODIFIES:
     * EFFECTS :
     */
    public UserProfile(String name, LayoutManager defaultLayout) {
        preferences = new PreferenceManager(new ArrayList<>(1)); // For now, there are no preferences.
        layout = new LayoutProfileManager(new LayoutProfile("Default", defaultLayout)); // I won't bother
        // localizing this
        this.name = name;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public PreferenceManager getPreferences() {
        return preferences;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    public LayoutProfileManager getLayoutProfileManager() {
        return layout;
    }


    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "P: " + name;
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getCommandString() {
        return "P";
    }

    /*
     * REQUIRES: none
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
