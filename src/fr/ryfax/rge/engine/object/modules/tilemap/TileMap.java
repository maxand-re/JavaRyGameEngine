package fr.ryfax.rge.engine.object.modules.tilemap;

import fr.ryfax.rge.engine.global.Engine;
import fr.ryfax.rge.engine.image.Image;
import fr.ryfax.rge.engine.image.ImageBuilder;
import fr.ryfax.rge.engine.object.VisualGameObject;
import fr.ryfax.rge.engine.utils.drawing.Drawer;
import fr.ryfax.rge.engine.utils.movements.Vector2D;
import fr.ryfax.rge.engine.utils.path.Resource;import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileMap implements VisualGameObject {

    private final Image[] tiles;
    private final int cellWidth;
    private final int cellHeight;
    private final int chunkSize = 16;
    private final int[] EMPTY_CHUNK = new int[chunkSize^2];
    private final Map<Vector2D, TileMapChunk> chunks = new HashMap<>();

    private Vector2D location = new Vector2D(0, 0);

    public void init(Engine engine) {}

    public TileMap(Resource resource, int baseX, int baseY, int width, int height) {
        Image image = new ImageBuilder(resource).build();

        cellWidth = width;
        cellHeight = height;

        Arrays.fill(EMPTY_CHUNK, -1);

        int nbCellsX = image.getWidth() / width;
        int nbCellsY = image.getHeight() / height;

        tiles = new Image[nbCellsX * nbCellsY];

        for (int y = 0; y < nbCellsY; y++) {
            for (int x = 0; x < nbCellsX; x++) {
                tiles[y*nbCellsX + x] = new ImageBuilder(image.getBufferedImage().getSubimage(baseX + x * width, baseY + y * height, width, height)).build();
            }
        }
    }

    public void removeCell(int x, int y) {
        Vector2D chunkPosition = new Vector2D(Math.floor(x/(float)chunkSize), Math.floor(y/(float)chunkSize));
        if(!chunks.containsKey(chunkPosition))
            chunks.put(chunkPosition, new TileMapChunk(chunkSize, this));
        chunks.get(chunkPosition).setCell(x, y, -1);

        if(Arrays.equals(chunks.get(chunkPosition).cells, EMPTY_CHUNK)) chunks.remove(chunkPosition);
    }

    /*
     * Setters
     */
    public void setLocation(Vector2D location) {
        this.location = location;
    }

    public void setCell(int x, int y, int id) {
        Vector2D chunkPosition = new Vector2D(Math.floor(x/(float)chunkSize), Math.floor(y/(float)chunkSize));

        if(!chunks.containsKey(chunkPosition))
            chunks.put(chunkPosition, new TileMapChunk(chunkSize, this));

        chunks.get(chunkPosition).setCell(x, y, id);
    }

    /*
     * Getters
     */
    public int getCell(int x, int y) {
        Vector2D chunkPosition = new Vector2D(Math.floor(x/(float)chunkSize), Math.floor(y/(float)chunkSize));

        if(chunks.containsKey(chunkPosition))
            return chunks.get(chunkPosition).cells[(y%chunkSize)*chunkSize + (x%chunkSize)];

        return -1;
    }

    public Image[] getTiles() { return tiles; }
    public Vector2D getLocation() { return location; }
    public Map<Vector2D, TileMapChunk> getChunks() { return chunks; }
    public int getChunkSize() { return chunkSize; }
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
    }

    public void update(double delta, int accumulator) {}
}
