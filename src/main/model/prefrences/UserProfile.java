package model.prefrences;

import ui.cli.LayoutManager;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends MenuBarItem {
    /*
     * Class Description:
     *
     */
    private final PreferenceManager preferences;
    private final LayoutProfileManager layout;

    private final String name;

    /*
     * REQUIRES:
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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public PreferenceManager getPreferences() {
        return preferences;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public LayoutProfileManager getLayoutProfileManager() {
        return layout;
    }


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "P: " + name;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getCommandString() {
        return "P";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
