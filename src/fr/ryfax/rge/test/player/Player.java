package fr.ryfax.rge.test.player;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.sprite.SpriteAnimation;
import fr.ryfax.rge.engine.global.sprite.SpriteSheetLoader;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.AnimatedSprite;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class Player {

    private Entity entity;

    private AnimatedSprite playerSprite = new AnimatedSprite();
    SpriteAnimation runRight;

    public Player(Engine engine) {
        entity = new Entity(engine);
        entity.setWidth(100);
        entity.setHeight(100);
        entity.setPosition(new Vector2D(200, 200));
        entity.addModule(playerSprite, 1);

        loadSprites();

        playerSprite.play(runRight);
    }

    private void loadSprites() {
        SpriteSheetLoader ssl = new SpriteSheetLoader("fr/ryfax/rge/assets/sprites/player.png");

        ssl.setCellSize(100, 100);
        ssl.load();

        runRight = new SpriteAnimation(ssl.getSprites());
        runRight.setTickRate(6);
        runRight.setStartIndex(6);
        runRight.setEndIndex(11);
    }


    /*
     * Getters
     */
    public Entity getEntity() { return entity; }
}
