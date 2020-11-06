package fr.ryfax.rge.engine.object.modules.particules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.object.modules.particules.emitters.Emitter;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Particules implements VisualGameObject {

    private Emitter emitter;
    private List<Particule> particules = new ArrayList<>();

    public Particules(Emitter emitter) {
        this.emitter = emitter;
    }

    public void init(Engine engine) {}

    public void update(double delta, int accumulator) {
        emitter.update(delta, particules);
    }

    public void draw(Drawer drawer) {
        for(Particule p : particules) {
            p.setColor(new Color(p.getColor().getRed(), p.getColor().getGreen(), p.getColor().getBlue(), (int) ((p.getLife() / 100) * 255)));
            drawer.setColor(p.getColor()).fillRect(p.getPosition(), new Dimension(p.getSize(), p.getSize()));
        }
    }

    public void emit() {
        emitter.emit(particules);
    }
}
