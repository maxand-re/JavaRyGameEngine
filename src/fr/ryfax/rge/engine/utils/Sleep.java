package fr.ryfax.rge.engine.utils;

import java.util.concurrent.locks.LockSupport;

public class Sleep {

    public static void sleep(int ms) {
        try { Thread.sleep(ms); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

    public static void sleepMicro(int micros) {
        LockSupport.parkNanos(micros * 1000);
    }

}
