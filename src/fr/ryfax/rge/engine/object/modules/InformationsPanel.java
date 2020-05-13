package fr.ryfax.rge.engine.object.modules;

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
    private int tick = 0;

    public void init(Engine engine) {
        Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW_BACKGROUND);
        statistics = engine.getStatistics();
        fontRenderer = new FontRenderer(font);

        version = fontRenderer.build("RGE Version 0.0.1");
    }

    public void update(int tick) {
        this.tick = tick;

        if(tick % 10 == 0) {
            fpsAndTick = fontRenderer.build("FPS: " + statistics.getCurrentFps() + " Average: " + statistics.getAverageFps());
            ticks = fontRenderer.build("Ticks: " + Tools.intToDigit(tick) + "/60" + " TPS: " + statistics.getCurrentTps());
            time = fontRenderer.build("Elapsed time: " + statistics.getElapsedTime());
            cam = fontRenderer.build("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                    " y: " + Tools.round(statistics.getCameraPosition().y, 0));
            ram = fontRenderer.build("RAM: " + statistics.getUsedRam() + "/" + statistics.getTotalRam() + "Mb");
        }
    }

    public void draw(Drawer d) {
        d.imageNotRelative(version, 5, 5);
        d.imageNotRelative(fpsAndTick, 5, 21);
        d.imageNotRelative(ticks, 5, 37);
        d.imageNotRelative(time, 5, 53);
        d.imageNotRelative(cam, 5, 69);
        d.imageNotRelative(ram, 5, 85);
    }

}
