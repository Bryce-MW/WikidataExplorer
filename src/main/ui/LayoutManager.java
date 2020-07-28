package ui;

import model.util.StringBuilderUtil;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class LayoutManager {
    //TODO: Implement
    private final ArrayList<ItemViewController> controllers;
    private final int width;
    private final int height;
    private int separationWidth;

    private final MenuBar menuBar;

    private int leftIndex;

    public LayoutManager(int width, int height, MenuBar menuBar) {
        this.width = width;
        this.height = height;
        this.menuBar = menuBar;
        controllers = new ArrayList<>(5);
    }

    public void add(ItemViewController newView) {
        controllers.add(newView);
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

    @TestOnly
    public ArrayList<StringBuilder> toStringArray() {
        ArrayList<StringBuilder> result = new ArrayList<>(height);
        result.addAll(menuBar.toStringArray());

        int startHeight = result.size() + separationWidth - 1; // Start separationWidth from menuBar

        IntStream.range(0, height - result.size()).mapToObj((i) -> new StringBuilder(100)).forEach(result::add);

        for (ItemViewController controller : controllers.subList(leftIndex, controllers.size())) {
            int height = startHeight;
            for (StringBuilder line : controller.toStringArray()) {
                result.get(height++).append(line);
            }

            StringBuilderUtil.padAll(result.subList(startHeight, result.size()), ' ', separationWidth);
            // TODO: Render connections
        }

        return result;
    }

    public void print() {
        ArrayList<StringBuilder> result = toStringArray();

        result.forEach(System.out::println);
    }

    //TODO: Rendering stuff
}
