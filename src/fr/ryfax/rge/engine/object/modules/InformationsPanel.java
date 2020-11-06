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
import fr.ryfax.rge.engine.utils.movements.Vector2D;

public class InformationsPanel implements VisualGameObject {

    // Variables
    private int timesPerSec = 100;
    private Statistics statistics;
    private FontRenderer fontRenderer;
    private Image version = null, fpsAndTick = null, ticks = null,
                  time = null, cam = null, ram = null;

    public void init(Engine engine) {
        Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW_BACKGROUND);
        statistics = engine.getStatistics();
        fontRenderer = new FontRenderer(font);

        version = fontRenderer.build("RGE Version " + Statistics.VERSION);
    }

    public InformationsPanel() {}
    public InformationsPanel(int timesPerSec) {
        this.timesPerSec = 1000 / timesPerSec;
    }

    public void update(double delta, int accumulator) {
        if(accumulator % timesPerSec == 0) {
            fpsAndTick = fontRenderer.build("FPS: " + statistics.getCurrentFps() + " Average: " + statistics.getAverageFps() + " Estimated: " + (int)(1000 / delta));
            ticks = fontRenderer.build("Render: " + Tools.round(delta, 0) + "ms ACC: " + accumulator);
            time = fontRenderer.build("Elapsed time: " + statistics.getElapsedTime());
            ram = fontRenderer.build("RAM: " + statistics.getUsedRam() + "/" + statistics.getTotalRam() + "Mb");
            cam = fontRenderer.build("Camera: x: " + Tools.round(statistics.getCameraPosition().x, 0) +
                    " y: " + Tools.round(statistics.getCameraPosition().y, 0) + " /" +
                    " rotation: " + Tools.round(Math.toRadians(SceneManager.getCurrentScene().getCamera().getRotation().degree), 0) +
                    " zoom: " + Tools.round(SceneManager.getCurrentScene().getCamera().getZoom(), 0));
        }
    }

    public void draw(Drawer d) {
        d.imageNotRelative(version, new Vector2D(5, 5));
        d.imageNotRelative(fpsAndTick, new Vector2D(5, 21));
        d.imageNotRelative(ticks, new Vector2D(5, 37));
        d.imageNotRelative(time, new Vector2D(5, 53));
        d.imageNotRelative(ram, new Vector2D(5, 69));
        d.imageNotRelative(cam, new Vector2D(5, 100));
    }

}
