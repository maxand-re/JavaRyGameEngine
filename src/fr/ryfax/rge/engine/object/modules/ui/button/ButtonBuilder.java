package fr.ryfax.rge.engine.object.modules.ui.button;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.drawing.scaler.Scaler;
import fr.ryfax.rge.engine.utils.drawing.scaler.ScalerLayout;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class ButtonBuilder {

    private Engine engine;
    private ScalerLayout layout;
    private ButtonListener listener = null;
    private Image background = null;
    private Vector2D position = new Vector2D(0, 0);
    private Dimension size = new Dimension(50, 25);
    private String text = "";
    private Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW);

    public ButtonBuilder(Engine engine) {
        this.engine = engine;
    }

    public void setListener(ButtonListener listener) { this.listener = listener; }
    public void setBackground(Image background) { this.background = background; }
    public void setPosition(Vector2D position) { this.position = position; }
    public void setScalerLayout(ScalerLayout layout) { this.layout = layout; }
    public void setSize(int width, int height) { this.size = new Dimension(width, height); }
    public void setText(String text) { this.text = text; }
    public void setFont(Font font) { this.font = font; }

    public Button build() {
        Scaler scaler = new Scaler(engine, layout, position, size);
        return new Button(scaler, background, text, listener, font);
    }

}
