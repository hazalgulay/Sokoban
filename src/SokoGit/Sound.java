package SokoGit;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[10];



    public Sound() {

        soundURL[0] = this.getClass().getResource("/sound/music6.wav");
        soundURL[1] = this.getClass().getResource("/sound/complete.wav");
        soundURL[2] = this.getClass().getResource("/sound/walk5.wav");
        soundURL[3] = this.getClass().getResource("/sound/move.wav");
        soundURL[4] = this.getClass().getResource("/sound/wallHit.wav");
        soundURL[5] = this.getClass().getResource("/sound/push.wav");
        soundURL[6] = this.getClass().getResource("/sound/fit.wav");
        soundURL[7] = this.getClass().getResource("/sound/tictac2.wav");
        soundURL[8] = this.getClass().getResource("/sound/fail.wav");

    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();

        }
    }

    public void play(){

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

            clip.stop();
    }

}