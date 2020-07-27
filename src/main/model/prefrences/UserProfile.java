package model.prefrences;

import ui.LayoutManager;
import ui.MenuBarItem;

import java.util.ArrayList;

public class UserProfile implements MenuBarItem {
    //TODO: Implement
    private PreferenceManager preferences;
    private LayoutProfileManager layout;

    private String name;

    public UserProfile(String name, LayoutManager defaultLayout) {
        preferences = new PreferenceManager(new ArrayList<>(1)); // For now, there are no preferences.
        layout = new LayoutProfileManager(new LayoutProfile("Default", defaultLayout)); // I won't bother
        // localizing this
        this.name = name;
    }

    public PreferenceManager getPreferences() {
        return preferences;
    }

    public LayoutProfileManager getLayout() {
        return layout;
    }

    //TODO: Other stuff to make this work


    @Override
    public String toString() {
        return "P: " + name;
    }
}
