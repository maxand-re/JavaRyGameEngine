package fr.ryfax.rge.engine.utils.drawing.font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoader {

    public static final String RGE_DEFAULT_FONT = "RGE_DEFAULT";

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private final HashMap<String, Font> fonts = new HashMap<>();

    public void setSpecialCharsSize(String nameFont, HashMap<Character, Integer> chars) {
        fonts.get(nameFont).setSizeChars(chars);
    }

    public void load(String nameFont, String path) {
        InputStream stream = classloader.getResourceAsStream(path);
        try {
            assert stream != null;
            BufferedImage buff = ImageIO.read(stream);
            fonts.put(nameFont, new Font(buff));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public Font getDefaultFont() { return fonts.get(RGE_DEFAULT_FONT); }
    public HashMap<String, Font> getLoadedFonts() { return fonts; }
}
