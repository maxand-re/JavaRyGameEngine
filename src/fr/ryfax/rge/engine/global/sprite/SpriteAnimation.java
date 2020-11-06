package fr.ryfax.rge.engine.global.sprite;

import fr.ryfax.rge.engine.image.Image;

import java.util.ArrayList;

public class SpriteAnimation {

    private ArrayList<Image> sprites;
    private final int[] idx = new int[] {0, 0};
    private int tickRate = 0;

    public SpriteAnimation(ArrayList<Image> sprites) {
        this.sprites = sprites;
    }


    /*
     * Getters
     */
    public ArrayList<Image> getSprites() { return sprites; }
    public int getTickRate() { return tickRate; }
    public int getStartIndex() { return idx[0]; }
    public int getEndIndex() { return idx[1]; }


    /*
     * Setters
     */
    public SpriteAnimation setTickRate(int tickRate) { this.tickRate = tickRate; return this; }
    public SpriteAnimation setStartIndex(int idx) { this.idx[0] = idx; return this; }
    public SpriteAnimation setEndIndex(int idx) { this.idx[1] = idx; return this; }

}
