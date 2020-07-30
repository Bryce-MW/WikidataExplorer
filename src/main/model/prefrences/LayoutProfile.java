package model.prefrences;

import ui.LayoutManager;

public class LayoutProfile {
    private final LayoutManager layout;
    private String name;

    public LayoutProfile(String name, LayoutManager layout) {
        this.layout = layout;
        this.name = name;
    }

    public LayoutManager getLayout() {
        return layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // I'm not sure if anything else is really needed? I'm going leave this as is for now and see later
}
