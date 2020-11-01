package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TileMap implements VisualGameObject {

    /*
     * TODO: Système chunk pour opti l'affichage
     */

    private final Image[] tiles;
    private final int cellWidth;
    private final int cellHeight;
    private final Map<Vector2D, TileMapChunk> chunks = new HashMap<>();

    private Vector2D location = new Vector2D(0, 0);

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

    public TileMap setLocation(Vector2D location) {
        this.location = location;
        return this;
    }

    public TileMap setCell(int x, int y, int id) {
        Vector2D pos = new Vector2D(x, y);
        Vector2D nullVec = new Vector2D(0, 0);
        if(!chunks.containsKey(new Vector2D(Math.floor(x/16), Math.floor(y/16)))) chunks.put(new Vector2D(Math.floor(x/16), Math.floor(y/16)), new TileMapChunk(16, this));
        chunks.get(new Vector2D(Math.floor(x/16), Math.floor(y/16))).cells.put(new Vector2D(x%16, y%16), id);
        chunks.get(new Vector2D(Math.floor(x/16), Math.floor(y/16))).build();
        return this;
    }

    public int getCell(int x, int y) {
        if(!chunks.containsKey(new Vector2D(Math.floor(x/16), Math.floor(y/16)))) chunks.put(new Vector2D(Math.floor(x/16), Math.floor(y/16)), new TileMapChunk(16, this));
        return chunks.get(new Vector2D(Math.floor(x/16), Math.floor(y/16))).cells.get(new Vector2D(x%16, y%16));
    }

    public Image[] getTiles() {
        return tiles;
    }

    public Map<Vector2D, TileMapChunk> getChunks() {
        return chunks;
    }

    public Vector2D getLocation() {
        return location;
    }

    public Vector2D getCellSize(){
        return new Vector2D(cellWidth, cellHeight);
    }

    /*
     * Visuals
     */
    public void draw(Drawer drawer) {
        for (Map.Entry<Vector2D, TileMapChunk> chunks: chunks.entrySet()) {
            chunks.getValue().build();
            drawer.image(chunks.getValue().chunkImg, location.x + chunks.getKey().x*(cellWidth*chunks.getValue().size), location.y + chunks.getKey().y*(cellHeight*chunks.getValue().size));
        }
    }

    public void update(int tick) {}
}
