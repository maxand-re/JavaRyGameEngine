package fr.ryfax.rge.engine.object.elements.entity.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.VisualEntityModule;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class VisualBox implements VisualEntityModule {

    private Entity entity;

    public void init(Engine engine, Entity entity) { this.entity = entity; }

    public void update(double delta, int accumulator) {}

    public void draw(Drawer drawer) {
        Vector2D pos = entity.getPosition();

        drawer.setColor(new Color(0x0161C1)).setLineWidth(3).borderRect(pos, entity.getSize()).setLineWidth(1)
                .line(pos, new Vector2D(pos.x + entity.getSize().getWidth(), pos.y + entity.getSize().getHeight()))
                .line(new Vector2D(pos.x + entity.getSize().getWidth(), pos.y), new Vector2D(pos.x, pos.y + entity.getSize().getHeight()));
    }

}
