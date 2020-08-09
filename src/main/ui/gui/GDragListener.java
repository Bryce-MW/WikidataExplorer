package ui.gui;

import ui.GUInterface;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GDragListener extends MouseMotionAdapter {
    private final GUInterface guInterface;

    public GDragListener(GUInterface guInterface) {
        this.guInterface = guInterface;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        guInterface.drag(e.getX(), e.getY());
    }
}
