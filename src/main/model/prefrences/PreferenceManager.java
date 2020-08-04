package model.prefrences;

import org.jetbrains.annotations.NotNull;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class PreferenceManager implements MenuBarItem {
    //TODO: Implement
    private final @NotNull ArrayList<UserPreference> preferences;

    public PreferenceManager(@NotNull List<UserPreference> preferences) {
        this.preferences = new ArrayList<>(preferences.size());
        this.preferences.addAll(preferences);
    }

    public @NotNull List<UserPreference> getPreferences() {
        return preferences;
    }

    //TODO: Rendering stuff


    @Override
    public @NotNull String toString() {
        return "E: Preferences";
    }

    @Override
    public @NotNull String getCommandString() {
        return "E";
    }

    @Override
    public @NotNull Boolean parse(List<String> subList) {
        return true;
    }
}
