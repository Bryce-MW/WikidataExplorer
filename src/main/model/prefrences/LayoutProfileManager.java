package model.prefrences;

import org.jetbrains.annotations.NotNull;
import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutProfileManager implements MenuBarItem {
    //TODO: Implement
    private final @NotNull ArrayList<LayoutProfile> profiles;
    private final LayoutProfile current;

    public LayoutProfileManager(LayoutProfile defaultProfile) {
        profiles = new ArrayList<>(5);
        profiles.add(defaultProfile);
        current = defaultProfile;
    }

    public @NotNull ArrayList<LayoutProfile> getProfiles() {
        return profiles;
    }

    public void addProfile(LayoutProfile profile) {
        profiles.add(profile);
    }

    //TODO: Graphics stuff


    @Override
    public @NotNull String toString() {
        return "L: " + current.getName();
    }

    @Override
    public @NotNull String getCommandString() {
        return "L";
    }

    @Override
    public @NotNull Boolean parse(List<String> subList) {
        return true;
    }
}
