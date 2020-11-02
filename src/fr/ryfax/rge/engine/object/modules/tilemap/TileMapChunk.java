package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class TileMapChunk {

    public final int size, cellSizeX, cellSizeY;
    public boolean modified = false;
    public final int[] cells;
    public BufferedImage chunkImg;
    public TileMap tileMap;

    public TileMapChunk(int size, TileMap tileMap){
        this.size = size;
        this.cellSizeX = (int) tileMap.getCellSize().x;
        this.cellSizeY = (int) tileMap.getCellSize().y;
        this.tileMap = tileMap;
        this.cells = new int[size*size];

        Arrays.fill(cells, -1);
    }

    public TileMapChunk build(){
        Image img = ImageBuilder.createBlankImage(size * cellSizeX, size * cellSizeY, true);
        Graphics2D g2d = (Graphics2D) img.getBufferedImage().getGraphics();

        int x = 0, y = 0;

        for(int tile : cells) {
            if(tile >= 0)
                g2d.drawImage(
                        tileMap.getTiles()[tile].getBufferedImage(),
                        (x * cellSizeX), (y * cellSizeY), null);

            x++;
            if(x == size) {
                y++;
                x = 0;
            }
        }

        chunkImg = img.getBufferedImage();
        modified = false;
        return this;
    }

}
