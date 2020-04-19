package fr.ryfax.rge.engine.utils.drawing.font;


import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Font {

    private final HashMap<Character, Image> chars = new HashMap<>();
    int defaultSpace;
    HashMap<Character, Integer> sizeChars;


    public Font(BufferedImage img, HashMap<Character, Integer> sizeChars, Color fontColor, Color backgroundColor, int defaultSpace, int[] shadow) {
        this.sizeChars = sizeChars;
        this.defaultSpace = defaultSpace;

        int c = 0;
        for(int y = 0; y < img.getHeight(); y += 16) {
            for(int x = 0; x < img.getWidth(); x += 16) {
                Image image = new ImageBuilder(img.getSubimage(x, y, 16, 16)).build();
                char ch = (char) c;

                if(sizeChars.containsKey(ch))
                    image.crop(0, 0, 0, sizeChars.get(ch) + defaultSpace);
                else
                    image.crop(0, 0, 0, defaultSpace);

                if(shadow[0] != 0 && shadow[1] != 0)
                    image.addShadow(shadow[0], shadow[1]);

                image.addBackgroundColor(backgroundColor);
                image.changeColor(fontColor);


                chars.put(ch, image);
                c++;
            }
        }
    }

    /*
     * Getters
     */
    public HashMap<Character, Image> getChars() { return chars; }
}
