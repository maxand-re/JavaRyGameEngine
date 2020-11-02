package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class TileMap implements VisualGameObject {

    private final Image[] tiles;
    private final int cellWidth;
    private final int cellHeight;
    private final int chunkSize = 16;
    private final Map<Vector2D, TileMapChunk> chunks = new HashMap<>();

    private Vector2D location = new Vector2D(0, 0);
    private boolean debug = false;

    public void init(Engine engine) {}

    public TileMap(Resource resource, int baseX, int baseY, int width, int height) {
        Image image = new ImageBuilder(resource).build();

        cellWidth = width;
        cellHeight = height;

        int nbCellsX = image.getWidth() / width;
        int nbCellsY = image.getHeight() / height;

        tiles = new Image[nbCellsX * nbCellsY];

        for (int y = 0; y < nbCellsY; y++) {
            for (int x = 0; x < nbCellsX; x++) {
                tiles[y*nbCellsX + x] = new ImageBuilder(image.getBufferedImage().getSubimage(baseX + x * width, baseY + y * height, width, height)).build();
            }
        }
    }

    public void build() {
        for (Map.Entry<Vector2D, TileMapChunk> chunks : chunks.entrySet())
            if(chunks.getValue().modified)
                chunks.getValue().build();
    }

    /*
     * Setters
     */
    public TileMap setLocation(Vector2D location) {
        this.location = location;
        return this;
    }

    public TileMap setCell(int x, int y, int id) {
        Vector2D chunkPosition = new Vector2D(Math.floor(x/(float)chunkSize), Math.floor(y/(float)chunkSize));

        if(!chunks.containsKey(chunkPosition))
            chunks.put(chunkPosition, new TileMapChunk(chunkSize, this));

        chunks.get(chunkPosition).cells[(y%chunkSize)*cellWidth + (x%chunkSize)] = id;
        chunks.get(chunkPosition).modified = true;

        return this;
    }



    /*
     * Getters
     */
    public int getCell(int x, int y) {
        Vector2D chunkPosition = new Vector2D(Math.floor(x/(float)chunkSize), Math.floor(y/(float)chunkSize));

        if(chunks.containsKey(chunkPosition))
            return chunks.get(chunkPosition).cells[(y%chunkSize)*cellWidth + (x%chunkSize)];

        return -1;
    }

    public Image[] getTiles() { return tiles; }
    public Vector2D getLocation() { return location; }
    public void setDebug(boolean debug) { this.debug = debug; }
    public Map<Vector2D, TileMapChunk> getChunks() { return chunks; }
    public Vector2D getCellSize(){ return new Vector2D(cellWidth, cellHeight); }

    /*
     * Visuals
     */
    public void draw(Drawer drawer) {
        for (Map.Entry<Vector2D, TileMapChunk> chunks : chunks.entrySet()) {
            drawer.image(
                    chunks.getValue().chunkImg,
                    location.x + chunks.getKey().x * (cellWidth * chunkSize),
                    location.y + chunks.getKey().y * (cellHeight * chunkSize));
        }

        if(debug) {
            Random r = new Random(6666L);
            for (Map.Entry<Vector2D, TileMapChunk> chunks : chunks.entrySet()) {
                drawer.setColor(new Color(r.nextInt(0xFFFFFF)));
                drawer.setLineWidth(3);
                drawer.borderRect(
                        location.x + chunks.getKey().x * (cellWidth * chunkSize),
                        location.y + chunks.getKey().y * (cellHeight * chunkSize),
                        chunks.getValue().chunkImg.getWidth(),
                        chunks.getValue().chunkImg.getHeight());
            }
        }
    }

    public void update(int tick) {}
}
