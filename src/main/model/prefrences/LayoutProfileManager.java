package model.prefrences;

import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutProfileManager extends MenuBarItem {
    //TODO: Implement
    private final ArrayList<LayoutProfile> profiles;
    private final LayoutProfile current;

    public LayoutProfileManager(LayoutProfile defaultProfile) {
        profiles = new ArrayList<>(5);
        profiles.add(defaultProfile);
        current = defaultProfile;
    }

    public ArrayList<LayoutProfile> getProfiles() {
        return profiles;
    }

    public void addProfile(LayoutProfile profile) {
        profiles.add(profile);
    }

    //TODO: Graphics stuff


    @Override
    public String toString() {
        return "L: " + current.getName();
    }

    @Override
    public String getCommandString() {
        return "L";
    }

    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
