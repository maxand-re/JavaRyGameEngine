package fr.ryfax.rge.engine.object.elements.entity.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.sprite.SpriteAnimation;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.VisualEntityModule;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

public class AnimatedSprite implements VisualEntityModule {

    private SpriteAnimation animation;
    private Entity entity;

    int imageIdx = 0;

    public AnimatedSprite(SpriteAnimation animation) {
        this();
        this.animation = animation;
    }

    public AnimatedSprite() {}

    public void init(Engine engine, Entity entity) { this.entity = entity; }

    public void update(double delta, int accumulator) {
        if(animation == null) return;

        if(delta % animation.getTickRate() == 0) {
            imageIdx++;

            if(imageIdx > animation.getEndIndex())
                imageIdx = animation.getStartIndex();
        }
    }

    public void draw(Drawer drawer) {
        if(animation == null) return;

        drawer.image(animation.getSprites().get(imageIdx), entity.getPosition());
    }

    public void play(SpriteAnimation animation) {
        this.animation = animation;
        this.imageIdx = animation.getStartIndex();
    }

}
