package fr.ryfax.rge.engine.global.listeners;

import fr.ryfax.rge.engine.global.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseEvents implements MouseListener {

    private Engine engine;

    public MouseEvents(Engine engine) {
        this.engine = engine;
    }

    public void mouseClicked(MouseEvent mouseEvent) {}

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
}
