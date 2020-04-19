package fr.ryfax.rge.engine.utils.drawing.font;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Font {

    /*
     * TODO: Faire de cette class une class de "Font" et faire une class Text en mettant comme paramètre le font,
     *  et dans Text on pourra build un text, avec les couleurs de notre choix et non changer la couleurs pour tout la font
     */

    private final HashMap<Character, BufferedImage> chars = new HashMap<>();
    private HashMap<Character, Integer> sizeChars = new HashMap<>();

    public Font(BufferedImage img, HashMap<Character, Integer> sizeCharsRemove) {
        this(img);
        sizeChars = sizeCharsRemove;
    }

    public Font(BufferedImage img) {
        int c = 0;
        for(int y = 0; y < img.getHeight(); y += 16) {
            for(int x = 0; x < img.getWidth(); x += 16) {
                chars.put((char) c, img.getSubimage(x, y, 16, 16));
                c++;
            }
        }
    }

    /*
     * Getters
     */
    public HashMap<Character, Integer> getSizeChars() { return sizeChars; }
    public HashMap<Character, BufferedImage> getChars() { return chars; }

    /*
     * Setters
     */
    public void setSizeChars(HashMap<Character, Integer> sizeChars) {
        this.sizeChars = sizeChars;
    }
}
