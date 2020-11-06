package fr.ryfax.rge.engine.audio;

import javax.sound.sampled.*;

public class Audio {

    private Clip clip;

    public Audio(AudioInputStream stream) {
        try {
            AudioFormat format = stream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void play(boolean loop) {
        if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.setFramePosition(0);
        clip.start();
    }

    public Audio volume(float volume) {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(20f * (float) Math.log10(volume));
        return this;
    }

    public void stop() {
        clip.stop();
    }

}
