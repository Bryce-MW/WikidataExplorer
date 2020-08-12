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
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public LayoutProfile(String name, LayoutManager layout) {
        this.layout = layout;
        this.name = name;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public LayoutManager getLayout() {
        return layout;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public String getName() {
        return name;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public void setName(String name) {
        this.name = name;
    }

    // I'm not sure if anything else is really needed? I'm going leave this as is for now and see later
}
