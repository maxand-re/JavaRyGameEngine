package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.Font;
import fr.ryfax.rge.engine.utils.drawing.FontLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InformationsPanel implements VisualGameObject {

    private Statistics statistics;
    private int tick = 0;

    private Font font = FontLoader.getFonts().get("main");
    private final BufferedImage version = font.buildText("RGE Version 0.0.1", null, new Color(0, 0, 0, 125));
    private BufferedImage fpsAndTick = null, ticks = null, time = null, cam = null, ram = null;

    public InformationsPanel(Engine engine) { this.statistics = engine.getStatistics(); }

    public void update(int tick) { this.tick = tick; }

    public void draw(Drawer d) {
        if(tick == 15 || tick == 30 || tick == 45 || tick == 60) {
            fpsAndTick = font.buildText("FPS: " + statistics.getCurrentFps() + " TPS: " + statistics.getCurrentTps(), null, new Color(0, 0, 0, 125));
            ticks = font.buildText("Ticks: " + Tools.intToDigit(tick) + "/60", null, new Color(0, 0, 0, 125));
            time = font.buildText("Elapsed time: " + statistics.getElapsedTime(), null, new Color(0, 0, 0, 125));
            cam = font.buildText("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                    " y: " + Tools.round(statistics.getCameraPosition().y, 0), null, new Color(0, 0, 0, 125));
            ram = font.buildText("RAM: " + statistics.getUsedRam() + "/" + statistics.getTotalRam() + "mb", null, new Color(0, 0, 0, 125));
        }

        d.imageNotRelative(version, 0, 0);
        d.imageNotRelative(fpsAndTick, 0, 16);
        d.imageNotRelative(ticks, 0, 32);
        d.imageNotRelative(time, 0, 48);
        d.imageNotRelative(cam, 0, 64);
        d.imageNotRelative(ram, 0, 80);
    }

}
