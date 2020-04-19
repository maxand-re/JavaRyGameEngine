package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InformationsPanel implements VisualGameObject {

    /*
     * Vars
     */
    private final Statistics statistics;
    private final FontRenderer fontRenderer;
    private int tick = 0, drawedTick = 0;

    private final Color BACKGROUND_COLOR = new Color(0, 0, 0, 125);
    private final Color FONT_COLOR = new Color(255, 255, 255, 255);


    private final BufferedImage version;
    private BufferedImage fpsAndTick = null, ticks = null, time = null, cam = null, ram = null;

    /*
     * Methods
     */
    public InformationsPanel(Engine engine) {
        Font font = engine.getFontLoader().getDefaultFont();
        this.statistics = engine.getStatistics();
        this.fontRenderer = new FontRenderer(font);

        this.fontRenderer.setBackgroundColor(BACKGROUND_COLOR);
        this.fontRenderer.setFontColor(new Color(238, 198, 37));

        this.version = this.fontRenderer.build("RGE Version 0.0.1");

        this.fontRenderer.setFontColor(FONT_COLOR);
    }

    public void update(int tick) { this.tick = tick; }

    public void draw(Drawer d) {
        if(tick % 10 == 0 && drawedTick != tick) {

            fpsAndTick = fontRenderer.build("FPS: " + statistics.getCurrentFps() + " Average: " + statistics.getAverageFps());
            ticks = fontRenderer.build("Ticks: " + Tools.intToDigit(tick) + "/60" + " TPS: " + statistics.getCurrentTps());
            time = fontRenderer.build("Elapsed time: " + statistics.getElapsedTime());
            cam = fontRenderer.build("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                    " y: " + Tools.round(statistics.getCameraPosition().y, 0));
            ram = fontRenderer.build("RAM: " + statistics.getUsedRam() + "/" + statistics.getTotalRam() + "Mb");
            drawedTick = tick;
        }


        d.imageNotRelative(version, 0, 0);
        d.imageNotRelative(fpsAndTick, 0, 16);
        d.imageNotRelative(ticks, 0, 32);
        d.imageNotRelative(time, 0, 48);
        d.imageNotRelative(cam, 0, 64);
        d.imageNotRelative(ram, 0, 80);
    }

}
