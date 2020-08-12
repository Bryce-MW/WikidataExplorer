package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GDragListener extends MouseMotionAdapter {
    /*
     * Class Description:
     *
     */
    private final GUInterface guInterface;

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public GDragListener(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        guInterface.drag(e.getX(), e.getY());
    }
}
