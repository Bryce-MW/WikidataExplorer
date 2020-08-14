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
     * REQUIRES: defaultProfile is not null
     * MODIFIES: this
     * EFFECTS : creates a new profile manager
     */
    public LayoutProfileManager(LayoutProfile defaultProfile) {
        profiles = new ArrayList<>(5);
        profiles.add(defaultProfile);
        current = defaultProfile;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the list of profiles which this manager manages
     */
    public ArrayList<LayoutProfile> getProfiles() {
        return profiles;
    }

    /*
     * REQUIRES: profile is not null
     * MODIFIES: this
     * EFFECTS : adds a profile to this manager
     */
    public void addProfile(LayoutProfile profile) {
        profiles.add(profile);
    }


    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the CLI representation of the profile manager
     */
    @Override
    public String toString() {
        return "L: " + current.getName();
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : returns the string which is used by the CLI to direct commands to this manager
     */
    @Override
    public String getCommandString() {
        return "L";
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : parses a REPL command directed to this manager
     */
    @Override
    public Boolean parse(List<String> subList) {
        return true;
    }
}
