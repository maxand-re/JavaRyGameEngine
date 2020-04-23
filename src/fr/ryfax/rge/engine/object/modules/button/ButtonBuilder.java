package fr.ryfax.rge.engine.object.modules.button;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.utils.drawing.font.Font;
import fr.ryfax.rge.engine.utils.drawing.font.FontLoader;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class ButtonBuilder {

    private ButtonListener listener = null;
    private Image background = null;
    private Vector2D position = new Vector2D(0, 0);
    private Dimension size = new Dimension(50, 25);
    private String text = "";
    private Font font = FontLoader.getLoadedFonts().get(FontLoader.RGE_SHADOW);

    public void setListener(ButtonListener listener) { this.listener = listener; }
    public void setBackground(Image background) { this.background = background; }
    public void setPosition(Vector2D position) { this.position = position; }
    public void setSize(int width, int height) { this.size = new Dimension(width, height); }
    public void setText(String text) { this.text = text; }
    public void setFont(Font font) { this.font = font; }

    public Button build() {
        return new Button(position, size, background, text, listener, font);
    }

}
