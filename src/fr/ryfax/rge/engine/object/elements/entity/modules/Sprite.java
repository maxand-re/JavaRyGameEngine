package fr.ryfax.rge.engine.object.elements.entity.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.VisualEntityModule;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

public class Sprite implements VisualEntityModule {

    private Image image;
    private Entity entity;

    public Sprite(Image image) {
        this.image = image;
    }

    public void init(Engine engine, Entity entity) {
        this.entity = entity;
        this.image.resize((int) entity.getWidth(), (int) entity.getHeight());
    }

    public void update(int tick) {}

    public void draw(Drawer drawer) {
        drawer.image(image, entity.getPosition().x, entity.getPosition().y);
    }

    /*
     * Setters
     */
    public void setSize(int width, int height) { image.resize(width, height); }


}
