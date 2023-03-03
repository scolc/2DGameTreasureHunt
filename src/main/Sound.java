package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    private Clip clip;
    private URL soundURL[]; // for storing path names

    public Sound() {

        soundURL = new URL[30];
        soundURL[0] = getClass().getResource("/sound/world.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/open.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }

    // Getters and Setters


    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public URL[] getSoundURL() {
        return soundURL;
    }

    public void setSoundURL(URL[] soundURL) {
        this.soundURL = soundURL;
    }

    // Methods

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); // import the file in the given path name
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch(Exception e) {

        }

    }

    public void play() {

        clip.start();

    }

    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();
    }
}
