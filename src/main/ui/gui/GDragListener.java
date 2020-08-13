package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GDragListener extends MouseMotionAdapter {
    /*
     * Class Description:
     * This class acts to process the muse drag events for te GUI. It could probably have been better done as a
     * anonymous class but I was not sure how much code this would need so I made it a full class just in case.
     */
    private final GUInterface guInterface;

    /*
     * REQUIRES: the interface is valid and has been (or will be shortly) set up
     * MODIFIES:
     * EFFECTS :
     */
    public GDragListener(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    /*
     * REQUIRES: the event has an X and Y location
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        guInterface.drag(e.getX(), e.getY());
    }
}
