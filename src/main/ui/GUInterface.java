package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import model.prefrences.LayoutProfileManager;
import model.prefrences.PreferenceManager;
import model.prefrences.UserProfile;
import org.jetbrains.annotations.NotNull;
import ui.cli.LayoutManager;
import ui.cli.MenuBar;
import ui.cli.*;
import ui.gui.DragLayout;
import ui.gui.GDragListener;
import ui.gui.GMouseAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class GUInterface extends JPanel {
    public static final Color darkGray = new Color(43, 43, 43);
    public static final Color midGray = new Color(49, 51, 53);
    public static final Color brightGray = new Color(60, 63, 65);
    private final Map<Component, Point> locations = new HashMap<>();
    private boolean pressed;
    private int startX;
    private int startY;
    private Component down;

    public static void main(String[] args) throws NotFoundException {
        GUInterface gui = new GUInterface();
        DatumQueryService queryService =
                new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json")));
        queryService.triggerLoad();
        ItemView q42 = new ItemView(new Item("Q42", queryService));

        MenuBar menuBar = setupMenubar(gui, queryService);

        gui.setLayout(new DragLayout());
        gui.setUpWindow(q42, menuBar);
    }

    @NotNull
    private static MenuBar setupMenubar(GUInterface gui, DatumQueryService queryService) {
        ArrayList<MenuBarItem> menuItems = new ArrayList<>(3);
        SearchBar mainSearch = new SearchBar(new ScopedSearch((Item) null, queryService));
        MenuBar menuBar = new MenuBar(menuItems, mainSearch, WIDTH);
        JButton save = new JButton("Save");
        save.addActionListener(i -> queryService.triggerSave());
        menuBar.add(save);
        JButton load = new JButton("Load");
        load.addActionListener(i -> queryService.triggerLoad());
        menuBar.add(load);
        menuBar.setGuInterface(gui);
        UserProfile profile = new UserProfile("Default", new LayoutManager(0, 0, menuBar));
        LayoutProfileManager profileLayouts = profile.getLayoutProfileManager();
        PreferenceManager preferences = profile.getPreferences();
        menuItems.add(profileLayouts);
        menuItems.add(profile);
        menuItems.add(preferences);
        return menuBar;
    }

    private void setUpWindow(ItemView inital, MenuBar menuBar) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Wikidata Explorer");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBackground(darkGray);
            frame.setJMenuBar(menuBar);
            frame.add(this);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setSize(1600, 822);
            frame.setVisible(true);
            frame.addMouseListener(new GMouseAdapter(this));
            frame.addMouseMotionListener(new GDragListener(this));

            this.add(inital);
        });
    }

    public void pressed(int x, int y) {
        pressed = true;
        Point pos = getMousePosition();
        startX = pos.x;
        startY = pos.y;
        down = getComponentAt(pos.x, pos.y);
    }


    public void released(int x, int y) {
        pressed = false;
        Point pos = getMousePosition();
        if (pos != null) {
            startX = pos.x;
            startY = pos.y;
        }
    }

    public void drag(int x, int y) {
        Point pos = getMousePosition();
        if (pos != null) {
            if (pressed) {
                moveComponent(pos.x, pos.y, down);
                if (down instanceof GUInterface) {
                    for (Component component : getComponents()) {
                        moveComponent(pos.x, pos.y, component);
                    }
                }
            }
            startX = pos.x;
            startY = pos.y;
        }
    }

    private void moveComponent(int x, int y, Component component) {
        if (component instanceof ItemView) {
            ItemView item = (ItemView) component;
            Point location = item.getLocation();
            int origX = (int) location.getX();
            int origY = (int) location.getY();
            item.setLocation(x - startX + origX, y - startY + origY);
        }
    }

    public void toggle(ItemView value) {
        List<Component> components = Arrays.asList(getComponents());
        if (components.contains(value)) {
            Component toRemove = components.get(components.indexOf(value));
            remove(toRemove);
        } else {
            add(value);
        }
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }
}