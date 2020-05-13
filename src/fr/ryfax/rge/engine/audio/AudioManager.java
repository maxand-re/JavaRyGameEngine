package fr.ryfax.rge.engine.audio;

import fr.ryfax.rge.engine.utils.path.Resource;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.util.HashMap;

public class AudioManager {

    private static final HashMap<String, Audio> audios = new HashMap<>();

    public static void load(String name, Resource resource) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(resource.getStream());
            System.out.println(resource.getResource().toString());
            audios.put(name, new Audio(stream));
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static Audio get(String name) {
        return audios.get(name);
    }

}
