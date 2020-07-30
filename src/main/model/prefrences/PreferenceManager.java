package model.prefrences;

import ui.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager implements MenuBarItem {
    //TODO: Implement
    private final ArrayList<UserPreference> preferences;

    public PreferenceManager(List<UserPreference> preferences) {
        this.preferences = new ArrayList<>(preferences.size());
        this.preferences.addAll(preferences);
    }

    public List<UserPreference> getPreferences() {
        return preferences;
    }

    //TODO: Rendering stuff


    @Override
    public String toString() {
        return "E: Preferences";
    }

    @Override
    public String getCommandString() {
        return "E";
    }

    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
