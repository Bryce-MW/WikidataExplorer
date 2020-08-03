package ui.cli;

import java.util.List;
import java.util.function.Function;

public interface MenuBarItem {
    String getCommandString();

    default Function<List<String>, Boolean> getParser() {
        return this::parse;
    }

    Boolean parse(List<String> subList);

    //TODO: Implement

    //TODO: Rendering Stuff
}
