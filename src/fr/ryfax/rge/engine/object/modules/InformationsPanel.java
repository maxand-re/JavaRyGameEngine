package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.scenes.SceneManager;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;

public class InformationsPanel implements VisualGameObject {

    // Variables
    private Statistics statistics;
    private FontRenderer fontRenderer;
    private Image version = null, fpsAndTick = null, ticks = null, time = null, cam = null, ram = null;

    public void init(Engine engine) {
        Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW_BACKGROUND);
        statistics = engine.getStatistics();
        fontRenderer = new FontRenderer(font);

        version = fontRenderer.build("RGE Version " + statistics.VERSION);
    }

    public void update(int tick) {
        if(tick % 5 == 0) {
            fpsAndTick = fontRenderer.build("FPS: " + statistics.getCurrentFps() + " Average: " + statistics.getAverageFps());
            ticks = fontRenderer.build("Ticks: " + Tools.intToDigit(tick) + "/60" + " TPS: " + statistics.getCurrentTps());
            time = fontRenderer.build("Elapsed time: " + statistics.getElapsedTime());
            ram = fontRenderer.build("RAM: " + statistics.getUsedRam() + "/" + statistics.getTotalRam() + "Mb");
            cam = fontRenderer.build("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                    " y: " + Tools.round(statistics.getCameraPosition().y, 0) + " /" +
                    " r: " + Tools.round(Math.toRadians(SceneManager.getCurrentScene().getCamera().getRotation().degree), 0) + "rad");
        }
    }

    public void draw(Drawer d) {
        d.imageNotRelative(version, 5, 5);
        d.imageNotRelative(fpsAndTick, 5, 21);
        d.imageNotRelative(ticks, 5, 37);
        d.imageNotRelative(time, 5, 53);
        d.imageNotRelative(ram, 5, 69);
        d.imageNotRelative(cam, 5, 100);
    }

}
