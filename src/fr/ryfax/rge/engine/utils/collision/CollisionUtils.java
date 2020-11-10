package fr.ryfax.rge.engine.utils.collision;

import fr.ryfax.rge.engine.utils.movements.Vector2D;

import java.awt.*;

public class CollisionUtils {

    public static boolean vectorIsWithin(Vector2D pos, Vector2D smallestPos, Vector2D biggestPos){
        return (pos.x >= smallestPos.x && pos.x <= biggestPos.x) && (pos.y >= smallestPos.y && pos.y <= biggestPos.y);
    }
    public static boolean vectorIsWithin(Vector2D pos, Vector2D smallestPos, Dimension size){
        return (pos.x >= smallestPos.x && pos.x <= smallestPos.x+size.width) && (pos.y >= smallestPos.y && pos.y <= smallestPos.y+size.height);
    }

}
