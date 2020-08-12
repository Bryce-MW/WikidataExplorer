package model.prefrences;

import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager extends MenuBarItem {
    /*
     * Class Description:
     * This class would have been a menu of all of the possible preferences which could have been set. I never ended
     * up implementing any preferences so this class does nt do very much at the moment.
     */
    private final ArrayList<UserPreference> preferences;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public PreferenceManager(List<UserPreference> preferences) {
        this.preferences = new ArrayList<>(preferences.size());
        this.preferences.addAll(preferences);
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public List<UserPreference> getPreferences() {
        return preferences;
    }


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "E: Preferences";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getCommandString() {
        return "E";
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
