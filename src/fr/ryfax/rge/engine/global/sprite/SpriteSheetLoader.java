package fr.ryfax.rge.engine.global.sprite;

import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheetLoader {

    private final Image image;
    private final ArrayList<Image> sprites = new ArrayList<>();

    private Dimension size = new Dimension(16, 16);
    private Dimension resizedSize = new Dimension(-1, -1);

    public SpriteSheetLoader(Resource resource) { image = new ImageBuilder(resource).build(); }
    public SpriteSheetLoader(Image image) { this.image = image; }
    public SpriteSheetLoader(BufferedImage image) { this.image = new ImageBuilder(image).build(); }

    public SpriteSheetLoader setCellSize(Dimension size){
        this.size = size;
        return this;
    }

    public SpriteSheetLoader setSpritesSize(Dimension resizedSize){
        this.resizedSize = resizedSize;
        return this;
    }

    public ArrayList<Image> load() {
        BufferedImage img = image.getBufferedImage();
        for(int y = 0; y < img.getHeight(); y += size.getHeight()) {
            for(int x = 0; x < img.getWidth(); x += size.getWidth()) {
                Image sprite = new ImageBuilder(img.getSubimage(x, y, (int) size.getWidth(), (int) size.getHeight())).build();

                if((resizedSize.getHeight() != size.getHeight() && resizedSize.getHeight() != -1)
                        && (resizedSize.getWidth() != size.getWidth() && resizedSize.getWidth() != -1))
                            sprite.resize((int) resizedSize.getWidth(), (int) resizedSize.getHeight());

                sprites.add(sprite);
            }
        }

        return sprites;
    }

    public ArrayList<Image> getSprites() { return sprites; }
}
