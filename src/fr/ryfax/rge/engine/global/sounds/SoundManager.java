package fr.ryfax.rge.engine.global.sounds;

import javax.sound.sampled.*;
import java.util.HashMap;

public class SoundManager {


    public static HashMap<String, Sound> sounds = new HashMap<>();

    public static void load(String name, String path) {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            AudioInputStream stream = AudioSystem.getAudioInputStream(cl.getResourceAsStream(path));
            sounds.put(name, new Sound(stream));
        }catch (Exception e) {
            System.err.println("Can't load the sound!");
        }
    }


    public static void play(String name) {
        Sound sound = sounds.get(name);
        sound.play(false);
    }

    public static void play(String name, boolean loop) {
        Sound sound = sounds.get(name);
        sound.play(loop);
    }

    /*public static void stop(String name) {
        sounds.get(name).stop();
    }*/

}
