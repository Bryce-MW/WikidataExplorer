package model.prefrences;

import ui.cli.LayoutManager;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends MenuBarItem {
    //TODO: Implement
    private final PreferenceManager preferences;
    private final LayoutProfileManager layout;

    private final String name;

    public UserProfile(String name, LayoutManager defaultLayout) {
        preferences = new PreferenceManager(new ArrayList<>(1)); // For now, there are no preferences.
        layout = new LayoutProfileManager(new LayoutProfile("Default", defaultLayout)); // I won't bother
        // localizing this
        this.name = name;
    }

    public PreferenceManager getPreferences() {
        return preferences;
    }

    public LayoutProfileManager getLayoutProfileManager() {
        return layout;
    }

    //TODO: Other stuff to make this work


    @Override
    public String toString() {
        return "P: " + name;
    }

    @Override
    public String getCommandString() {
        return "P";
    }

    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
