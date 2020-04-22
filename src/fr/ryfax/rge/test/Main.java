package fr.ryfax.rge.test;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Parameters;
import fr.ryfax.rge.engine.global.sprite.SpriteAnimation;
import fr.ryfax.rge.engine.global.sprite.SpriteSheetLoader;
import fr.ryfax.rge.engine.global.scenes.Scene;
import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.object.elements.entity.Entity;
import fr.ryfax.rge.engine.object.elements.entity.modules.AnimatedSprite;
import fr.ryfax.rge.engine.object.modules.InformationsPanel;
import fr.ryfax.rge.engine.object.modules.button.Button;
import fr.ryfax.rge.engine.utils.Sleep;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.test.player.Player;
import fr.ryfax.rge.test.scenes.Introduction;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    /*
     * To test RGE
     */
    public static void main(String[] args) {
        Engine engine = new Engine("RGE - Example", 1280, 720);
        SceneManager sm = engine.getSceneManager();
        Parameters param = engine.getParameters();

        param.setLimitFps(false);
        param.setAntiAliasing(false);
        param.setQualityRendering(false);
        param.setCursor(Parameters.RGE_CURSOR_DEFAULT);
        param.setLimitOverload(Parameters.RGE_OVERLOAD_MEDIUM);
        param.setClearBufferColor(new Color(26, 150, 238));

        Scene game = engine.getSceneBuilder().setName("menu").build();
        Scene introScene = new Introduction(engine, game).getScene();
        Player player = new Player(engine);

        SpriteSheetLoader ssl = new SpriteSheetLoader("fr/ryfax/rge/assets/gui/button.png");
        ssl.setCellSize(401, 174);
        ssl.setSpritesSize(200, 87);
        ssl.load();

        Font font = engine.getFontLoader().getLoadedFonts().get(FontLoader.RGE_SHADOW);
        FontRenderer fr = new FontRenderer(font);

        Button button = new Button(engine, new ButtonEvents(ssl.getSprites()));
        button.setPosition(new Vector2D(200, 200));
        button.setSize(new Dimension(200, 87));
        button.setSprite(ssl.getSprites().get(0));
        button.setText(fr.build("Hello World!"));

        game.addGameObject(button, 1);
        game.addGameObject(player.getEntity(), 1);
        game.addGameObject(new InformationsPanel(engine), 1000);

        sm.setScene(introScene);

        engine.init();
    }

}
