package ui;

import model.util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class LayoutManager {
    //TODO: Implement
    private ArrayList<ItemViewController> controllers;
    private final int width;
    private final int height;
    private int defaultWidth;
    private int separationWidth;

    private final MenuBar menuBar;

    private int leftIndex;

    public LayoutManager(int width, int height, MenuBar menuBar) {
        this.width = width;
        this.height = height;
        this.menuBar = menuBar;
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

    public void setDefaultWidth(int width) {
        defaultWidth = width;
    }

    public void setSepWidth(int sepWidth) {
        separationWidth = sepWidth;
    }

    public void print() {
        ArrayList<StringBuilder> result = new ArrayList<>(height);
        result.addAll(menuBar.toStringArray());

        int startHeight = result.size() + separationWidth - 1; // Start separationWidth from menuBar

        StringBuilder blank = new StringBuilder(0);
        IntStream.range(0, height - result.size()).mapToObj((i) -> blank).forEach(result::add);

        for (ItemViewController controller : controllers.subList(leftIndex, controllers.size())) {
            int height = startHeight;
            for (StringBuilder line : controller.toStringArray()) {
                result.get(height++).append(line);
            }

            StringBuilderUtil.padAll(result.subList(startHeight, result.size()), ' ', separationWidth);
            // TODO: Render connections
        }

        result.forEach(System.out::println);
    }

    //TODO: Rendering stuff
}
