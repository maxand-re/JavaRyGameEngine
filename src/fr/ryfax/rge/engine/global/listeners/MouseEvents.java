package fr.ryfax.rge.engine.global.listeners;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.GameObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener, GameObject {

    private final Engine engine;
    private Point p = new Point(0, 0);

    public void init(Engine engine) { }

    public MouseEvents(Engine engine) {
        this.engine = engine;
    }

    public void mousePressed(MouseEvent mouseEvent) {
        if(!engine.getButtonsPressed().contains(mouseEvent.getButton()))
            engine.getButtonsPressed().add(mouseEvent.getButton());
        engine.getMouseListeners().forEach(list -> list.onButtonPressed(mouseEvent.getButton()));
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        engine.getButtonsPressed().remove((Object) mouseEvent.getButton());
        engine.getMouseListeners().forEach(list -> list.onButtonReleased(mouseEvent.getButton()));
    }

    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
    public void mouseClicked(MouseEvent mouseEvent) {}

    public void update(double delta, int accumulator) {
        if(engine.getMousePosition() != null && !engine.getMousePosition().equals(p)) {
            engine.getMouseListeners().forEach(list -> list.onMove(p));
            p = engine.getMousePosition();
        }
    }
}
