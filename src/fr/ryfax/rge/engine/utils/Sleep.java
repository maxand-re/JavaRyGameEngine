package fr.ryfax.rge.engine.utils;

public class Sleep {

    public static void sleep(int ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

}
