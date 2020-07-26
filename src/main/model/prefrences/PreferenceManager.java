package model.prefrences;

import ui.MenuBarItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PreferenceManager implements MenuBarItem {
    //TODO: Implement
    private final ArrayList<UserPreference> preferences;

    public PreferenceManager(Collection<? extends UserPreference> preferences) {
        this.preferences = new ArrayList<>(preferences.size());
        this.preferences.addAll(preferences);
    }

    public List<UserPreference> getPreferences() {
        return preferences;
    }

    //TODO: Rendering stuff
}
