package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.Tools;
import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.utils.drawing.Drawer;

import java.awt.*;

public class InformationsPanel implements VisualGameObject {

    private Statistics statistics;
    private int tick = 0;

    public InformationsPanel(Engine engine) { this.statistics = engine.getStatistics(); }

    public void update(int tick) { this.tick = tick; }

    public void draw(Drawer d) {
        d.fillRectNotRelative(8, 8, 184, 114, new Color(0xCD454545, true));
        d.fillRectNotRelative(10, 10, 180, 110, new Color(0xCD2F2F2F, true));

        d.textNotRelative("RGE Version 0.0.1", 20, 30, Color.WHITE);
        d.textNotRelative("FPS: " + statistics.getCurrentFps(), 20, 50);
        d.textNotRelative("Ticks: " + Tools.intToDigit(tick) + "/60", 20, 70);
        d.textNotRelative("Elapsed time: " + statistics.getElapsedTime(), 20, 90);
        d.textNotRelative("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                                        " y: " + Tools.round(statistics.getCameraPosition().y, 0), 20, 110);
    }

}
