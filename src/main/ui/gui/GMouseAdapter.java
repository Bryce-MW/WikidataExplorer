package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GMouseAdapter extends MouseAdapter {
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
    public GMouseAdapter(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public void mousePressed(MouseEvent e) {
        guInterface.pressed(e.getX(), e.getY());

    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        guInterface.released(e.getX(), e.getY());
    }
}
