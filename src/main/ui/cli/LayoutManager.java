package ui.cli;

import model.data.Value;
import model.util.StringBuilderUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LayoutManager {
    //TODO: Implement
    private final @NotNull ArrayList<ItemViewController> controllers;
    private final int width;
    private final int height;
    private final MenuBar menuBar;
    private int separationWidth;
    private int leftIndex;

    public LayoutManager(int width, int height, MenuBar menuBar) {
        this.width = width;
        this.height = height;
        this.menuBar = menuBar;
        controllers = new ArrayList<>(5);
    }

    public void add(@NotNull ItemViewController newView) {
        controllers.add(newView);
        newView.setManager(this);
    }

    public void add(int index, @NotNull ItemViewController newView) {
        controllers.add(index, newView);
        newView.setManager(this);
    }

    public boolean remove(ItemViewController toRemove) { // true if ItemViewController is now empty
        controllers.remove(toRemove);
        return controllers.isEmpty();
    }

    public boolean isEmpty() {
        return controllers.isEmpty();
    }

    public int size() {
        return controllers.size();
    }

    public void setSepWidth(int sepWidth) {
        separationWidth = sepWidth;
    }

    private @NotNull ArrayList<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(height);
        result.addAll(menuBar.toStringArray());

        int startHeight = result.size() + separationWidth - 1; // Start separationWidth from menuBar

        IntStream.range(0, height - result.size()).mapToObj((i) -> new StringBuilder(100)).forEach(result::add);

        for (ItemViewController controller : controllers.subList(leftIndex, controllers.size())) {
            int height = startHeight;
            int lastLength = 0;
            for (StringBuilder line : controller.toStringArray()) {
                if (height >= result.size()) {
                    StringBuilder toAdd = new StringBuilder(100);
                    StringBuilderUtil.pad(toAdd, ' ', lastLength);
                    result.add(toAdd);
                }
                lastLength = result.get(height).length();
                result.get(height++).append(line);
            }

            StringBuilderUtil.padAll(result.subList(startHeight, result.size()), ' ', separationWidth);
            // TODO: Render connections
        }

        return result;
    }

    public void print() {
        ArrayList<StringBuilder> result = toStringArray();

        result.stream().filter(builder -> builder.length() > width).forEach(builder -> builder.setLength(width));

        result.forEach(System.out::println);
    }

    public Boolean parse(List<String> instructions) {
        ArrayList<ItemViewController> toCheck = new ArrayList<>(controllers);
        return toCheck.stream().anyMatch((i) -> i.parse(instructions));
    }

    public boolean toggleLeft(Value statement, ItemViewController itemViewController) {
        int index = controllers.indexOf(itemViewController);
        if (index == -1) {
            return false;
        }
        if (index == 0) {
            add(0, new ItemViewController(new ItemView(statement)));
        } else {
            ItemViewController left = controllers.get(index - 1);
            left.toggle(statement);
        }
        return true;
    }

    public boolean toggleRight(Value statement, ItemViewController itemViewController) {
        int index = controllers.indexOf(itemViewController);
        if (index == -1) {
            return false;
        }
        if (index == controllers.size()) {
            add(new ItemViewController(new ItemView(statement)));
        } else {
            ItemViewController left = controllers.get(index + 1);
            left.toggle(statement);
        }
        return true;
    }

    //TODO: Rendering stuff
}
