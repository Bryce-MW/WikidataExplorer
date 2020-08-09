package ui.cli;

import javax.swing.*;
import java.util.List;
import java.util.function.Function;

public abstract class MenuBarItem extends JMenu {

    public abstract String getCommandString();

    public Function<List<String>, Boolean> getParser() {
        return this::parse;
    }

    public abstract Boolean parse(List<String> subList);

    //TODO: Implement

    //TODO: Rendering Stuff
}
