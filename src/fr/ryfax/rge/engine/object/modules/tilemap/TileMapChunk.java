package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileMapChunk {

    public final int size;
    public final HashMap<Vector2D, Integer> cells;
    public BufferedImage chunkImg;
    private final TileMap tileMap;

    public TileMapChunk(int size, TileMap tileMap){
        this.size = size;
        this.cells = new HashMap<>();
        this.tileMap = tileMap;
    }

    public TileMapChunk build(){
        Image img = ImageBuilder.createBlankImage((int) (size * tileMap.getCellSize().x + tileMap.getCellSize().x), (int) (size * tileMap.getCellSize().y + tileMap.getCellSize().y), true);
        Graphics2D g2d = (Graphics2D) img.getBufferedImage().getGraphics();

        for(Vector2D coords : cells.keySet())
            g2d.drawImage(tileMap.getTiles()[cells.get(coords)].getBufferedImage(), (int) (coords.x * tileMap.getCellSize().x), (int) (coords.y * tileMap.getCellSize().y), null);

        chunkImg = img.getBufferedImage();
        return this;
    }

}
