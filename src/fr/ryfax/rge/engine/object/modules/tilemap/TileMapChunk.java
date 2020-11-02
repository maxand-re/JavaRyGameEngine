package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.image.ImageBuilder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class TileMapChunk {

    public final int size, cellWidth, cellHeight;
    public final int[] cells;
    public BufferedImage chunkImg;
    public TileMap tileMap;

    public TileMapChunk(int size, TileMap tileMap){
        this.size = size;
        this.cellWidth = (int) tileMap.getCellSize().x;
        this.cellHeight = (int) tileMap.getCellSize().y;
        this.tileMap = tileMap;
        this.cells = new int[size*size];

        chunkImg = ImageBuilder.createBlankImage(size * cellWidth, size * cellHeight, true).getBufferedImage();

        Arrays.fill(cells, -1);
    }

    public void setCell(int x, int y, int id) {
        x %= size;
        y %= size;

        if(cells[y * size + x] != id) {
            cells[y * size + x] = id;

            Graphics2D g2d = (Graphics2D) chunkImg.getGraphics();
            g2d.setComposite(AlphaComposite.Clear);
            g2d.fillRect((x * cellWidth), (y * cellHeight), cellWidth, cellHeight);
            g2d.setComposite(AlphaComposite.SrcOver);

            if (id >= 0) {
                g2d.drawImage(tileMap.getTiles()[id].getBufferedImage(), (x * cellWidth), (y * cellHeight), null);
            }
        }
    }

}
