package model.prefrences;

import ui.cli.MenuBarItem;

import java.util.ArrayList;
import java.util.List;

public class LayoutProfileManager extends MenuBarItem {
    /*
     * Class Description:
     * This class would have managed all of the layouts which a particular user profile would have saved. This was
     * never implemented so this class does not do very much at the moment.
     */
    private final ArrayList<LayoutProfile> profiles;
    private final LayoutProfile current;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public LayoutProfileManager(LayoutProfile defaultProfile) {
        profiles = new ArrayList<>(5);
        profiles.add(defaultProfile);
        current = defaultProfile;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public ArrayList<LayoutProfile> getProfiles() {
        return profiles;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void addProfile(LayoutProfile profile) {
        profiles.add(profile);
    }


    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String toString() {
        return "L: " + current.getName();
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public String getCommandString() {
        return "L";
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
