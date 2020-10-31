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

    public void update(int tick) {}

    public void draw(Drawer drawer) {
        Vector2D pos = entity.getPosition();

        drawer.setColor(new Color(0x0161C1));
        drawer.setLineWidth(3);
        drawer.borderRect(pos.x, pos.y, entity.getWidth(), entity.getHeight());
        drawer.setLineWidth(1);
        drawer.line(pos.x, pos.y, pos.x + entity.getWidth(), pos.y + entity.getHeight());
        drawer.line(pos.x + entity.getWidth(), pos.y, pos.x, pos.y + entity.getHeight());
    }

}
