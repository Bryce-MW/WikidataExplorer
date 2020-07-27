package model.prefrences;

import ui.MenuBarItem;

import java.util.ArrayList;

public class LayoutProfileManager implements MenuBarItem {
    //TODO: Implement
    private ArrayList<LayoutProfile> profiles;
    private LayoutProfile current;

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
}
