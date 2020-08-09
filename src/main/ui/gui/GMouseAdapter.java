package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GMouseAdapter extends MouseAdapter {
    private final GUInterface guInterface;

    public GMouseAdapter(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        guInterface.pressed(e.getX(), e.getY());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        guInterface.released(e.getX(), e.getY());
    }
}
