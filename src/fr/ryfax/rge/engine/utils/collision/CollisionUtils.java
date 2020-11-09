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

    // those are to review i don't down with one of the two way is the better
    public static boolean rectIsWithin(Vector2D rectPos, Dimension rectSize, Vector2D smallestPos, Vector2D biggestPos){
        double diag = Math.sqrt(Math.pow(biggestPos.x - smallestPos.x, 2) + Math.pow(biggestPos.y - smallestPos.y, 2));
        return ((rectPos.x < diag/2.0) && (rectPos.x + rectSize.getWidth() > -diag/2.0) && (rectPos.y < diag/2.0) && (rectPos.y + rectSize.getHeight() > -diag/2.0));
        /*return (rectPos.x <= biggestPos.x && rectPos.x + rectSize.width >= smallestPos.x) &&
                (rectPos.y <= biggestPos.y && rectPos.y + rectSize.height >= smallestPos.y);*/
    }
    public static boolean rectIsWithin(Vector2D rectPos, Dimension rectSize, Vector2D smallestPos, Dimension size){
        double diag = Math.sqrt(Math.pow(size.getWidth(), 2) + Math.pow(size.getHeight() - smallestPos.y, 2));
        return ((rectPos.x < diag/2.0) && (rectPos.x + rectSize.getWidth() > -diag/2.0) && (rectPos.y < diag/2.0) && (rectPos.y + rectSize.getHeight() > -diag/2.0));
        /*return (rectPos.x <= smallestPos.x + size.width && rectPos.x + rectSize.width >= smallestPos.x) &&
                (rectPos.y <= smallestPos.y + size.height && rectPos.y + rectSize.height >= smallestPos.y);*/
    }

}
