package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GMouseAdapter extends MouseAdapter {
    /*
     * Class Description:
     * This class simply serves as the adapter for processing mouse presses from the GUI. It could probably be done
     * as an anonymous class, but I was unsure of how much code needed to go here which is why I made this a separate
     *  class.
     */
    private final GUInterface guInterface;

    /*
     * REQUIRES: guInterface is a valid interface and has been (or will be shortly) set up.
     * MODIFIES: this
     * EFFECTS : sets up this mouse adapter
     */
    public GMouseAdapter(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    /*
     * REQUIRES: the event has an X and Y location
     * MODIFIES: guInterface
     * EFFECTS : interprets a mouse press event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        guInterface.pressed(e.getX(), e.getY());

    }

    /*
     * REQUIRES: the event has an X and Y location
     * MODIFIES: guInterface
     * EFFECTS : interprets a mouse released event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        guInterface.released(e.getX(), e.getY());
    }
}
