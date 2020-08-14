package model.prefrences;

import ui.cli.LayoutManager;

public class LayoutProfile {
    /*
     * Class Description:
     * This class was never implemented but I believe that I had intended it to manage a specific layout of which a
     * profile would have multiple.
     */
    private final LayoutManager layout;
    private String name;

    /*
     * REQUIRES: name is not null, layout is not null
     * MODIFIES: this
     * EFFECTS : creates a new layout profile
     */
    public LayoutProfile(String name, LayoutManager layout) {
        this.layout = layout;
        this.name = name;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the layout that this profile is associated with
     */
    public LayoutManager getLayout() {
        return layout;
    }

    /*
     * REQUIRES: none
     * MODIFIES: none
     * EFFECTS : gets the name of this profile
     */
    public String getName() {
        return name;
    }

    /*
     * REQUIRES: name is not null
     * MODIFIES: none
     * EFFECTS : changes the name of this profile
     */
    public void setName(String name) {
        this.name = name;
    }

    // I'm not sure if anything else is really needed? I'm going leave this as is for now and see later
}
