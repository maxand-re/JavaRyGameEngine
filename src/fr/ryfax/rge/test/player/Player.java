package fr.ryfax.rge.test.player;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.sprite.SpriteAnimation;
import fr.ryfax.rge.engine.global.sprite.SpriteSheetLoader;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.AnimatedSprite;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.test.Main;

public class Player {

    private final Entity entity;

    private AnimatedSprite playerSprite;
    private SpriteAnimation runRight, runLeft, runTop, runBottom;

    public Player() {
        Engine engine = Main.getInstance();
        playerSprite = new AnimatedSprite();

        entity = new Entity();
        entity.setWidth(100);
        entity.setHeight(100);
        entity.setPosition(new Vector2D(200, 200));
        entity.addModule(playerSprite, 1);

        loadSprites();

        playerSprite.play(runRight);

        engine.addListener(new PlayerKeyboardEvents(this));
    }

    private void loadSprites() {
        SpriteSheetLoader ssl = new SpriteSheetLoader("fr/ryfax/rge/assets/textures/sprites/player.png");
        ssl.setCellSize(100, 100);
        ssl.setSpritesSize(100, 100);
        ssl.load();

        runRight = new SpriteAnimation(ssl.getSprites());
        runRight.setTickRate(5);
        runRight.setStartIndex(6);
        runRight.setEndIndex(11);

        runLeft = new SpriteAnimation(ssl.getSprites());
        runLeft.setTickRate(5);
        runLeft.setStartIndex(18);
        runLeft.setEndIndex(23);

        runBottom = new SpriteAnimation(ssl.getSprites());
        runBottom.setTickRate(5);
        runBottom.setStartIndex(0);
        runBottom.setEndIndex(5);

        runTop = new SpriteAnimation(ssl.getSprites());
        runTop.setTickRate(5);
        runTop.setStartIndex(12);
        runTop.setEndIndex(17);
    }

    public void playAnimation(PlayerAnimation animation) {
        switch (animation) {
            case UP:
                playerSprite.play(runTop);
                break;
            case DOWN:
                playerSprite.play(runBottom);
                break;
            case LEFT:
                playerSprite.play(runLeft);
                break;
            case RIGHT:
                playerSprite.play(runRight);
                break;
        }
    }


    /*
     * Getters
     */
    public Entity getEntity() { return entity; }
}
