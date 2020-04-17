package fr.ryfax.rge.engine.utils.drawing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FontLoader {

    private final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private static HashMap<String, Font> fonts = new HashMap<>();

    public void addFile(String name, String path) {
        InputStream stream = classloader.getResourceAsStream(path);
        try {
            assert stream != null;
            BufferedImage buff = ImageIO.read(stream);

            fonts.put(name, new Font(buff));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static HashMap<String, Font> getFonts() { return fonts; }
}
