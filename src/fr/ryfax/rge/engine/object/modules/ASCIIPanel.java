package fr.ryfax.rge.engine.object.modules;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.global.Statistics;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIPanel implements VisualGameObject {

    /*
     * Vars
     */
    private final Statistics statistics;
    private final FontRenderer fontRenderer;
    private int tick = 0, drawedTick = 0;


    private final BufferedImage line1, line2, line3;

    /*
     * Methods
     */
    public ASCIIPanel(Engine engine) {
        Font font = engine.getFontLoader().getDefaultFont();
        this.fontRenderer = new FontRenderer(font);
        this.statistics = engine.getStatistics();

        this.fontRenderer.setSize(48);

        this.line1 = this.fontRenderer.build("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.line2 = this.fontRenderer.build("abcdefghijklmnopqrstuvwxyz");
        this.line3 = this.fontRenderer.build("0123456789?!.:/\\§=");
    }

    public void update(int tick) { this.tick = tick; }

    public void draw(Drawer d) {
        d.imageNotRelative(line1, 200, 100);
        d.imageNotRelative(line2, 200, 100 + this.fontRenderer.getSize());
        d.imageNotRelative(line3, 200, 100 + this.fontRenderer.getSize()*2);
    }

}
