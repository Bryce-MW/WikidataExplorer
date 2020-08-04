package model.prefrences;

import org.jetbrains.annotations.NotNull;
import ui.cli.LayoutManager;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class UserProfile implements MenuBarItem {
    //TODO: Implement
    private final @NotNull PreferenceManager preferences;
    private final @NotNull LayoutProfileManager layout;

    private final String name;

    public UserProfile(String name, LayoutManager defaultLayout) {
        preferences = new PreferenceManager(new ArrayList<>(1)); // For now, there are no preferences.
        layout = new LayoutProfileManager(new LayoutProfile("Default", defaultLayout)); // I won't bother
        // localizing this
        this.name = name;
    }

    public @NotNull PreferenceManager getPreferences() {
        return preferences;
    }

    public @NotNull LayoutProfileManager getLayout() {
        return layout;
    }

    //TODO: Other stuff to make this work


    @Override
    public @NotNull String toString() {
        return "P: " + name;
    }

    @Override
    public @NotNull String getCommandString() {
        return "P";
    }

    @Override
    public @NotNull Boolean parse(List<String> subList) {
        return true;
    }
}
