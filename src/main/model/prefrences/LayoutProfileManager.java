package model.prefrences;

import ui.MenuBarItem;

import java.util.ArrayList;

public class LayoutProfileManager implements MenuBarItem {
    //TODO: Implement
    private ArrayList<LayoutProfile> profiles;

    public LayoutProfileManager() {
        profiles = new ArrayList<>(5);
    }

    public ArrayList<LayoutProfile> getProfiles() {
        return profiles;
    }

    public void addProfile(LayoutProfile profile) {
        profiles.add(profile);
    }

    //TODO: Graphics stuff
}
