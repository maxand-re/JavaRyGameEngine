package fr.ryfax.rge.engine.global.sprite;

import fr.ryfax.rge.engine.global.image.Image;
import fr.ryfax.rge.engine.global.image.ImageBuilder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheetLoader {

    private Image image;
    private ArrayList<Image> sprites = new ArrayList<>();

    private int width = 16, height = 16;
    private int resizedWidth = -1, resizedHeigth = -1;

    public SpriteSheetLoader(String path) { image = new ImageBuilder(path).build(); }
    public SpriteSheetLoader(Image image) { this.image = image; }
    public SpriteSheetLoader(BufferedImage image) { this.image = new ImageBuilder(image).build(); }

    public void setCellSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void setSpritesSize(int width, int height){
        resizedWidth = width;
        resizedHeigth = height;
    }

    public ArrayList<Image> load() {
        BufferedImage img = image.getBufferedImage();
        for(int y = 0; y < img.getHeight(); y += height) {
            for(int x = 0; x < img.getWidth(); x += width) {
                Image sprite = new ImageBuilder(img.getSubimage(x, y, width, height)).build();

                if((resizedHeigth != height && resizedHeigth != -1)
                        && (resizedWidth != width && resizedWidth != -1))
                            sprite.resize(resizedWidth, resizedHeigth);

                sprites.add(sprite);
            }
        }

        return sprites;
    }

    public ArrayList<Image> getSprites() { return sprites; }
}
