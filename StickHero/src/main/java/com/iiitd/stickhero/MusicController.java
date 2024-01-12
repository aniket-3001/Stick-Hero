package com.iiitd.stickhero;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class MusicController extends StickHero{
    private MediaPlayer mediaPlayer1;

    public MediaPlayer getMediaPlayer1() {
        return mediaPlayer1;
    }

    public void setMediaPlayer1(MediaPlayer mediaPlayer1) {
        this.mediaPlayer1 = mediaPlayer1;
    }

    public void bgMusic() {
        String musicName = "src\\main\\resources\\gamebg.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        setBgPlayer(new MediaPlayer(media));
        getBgPlayer().setCycleCount(MediaPlayer.INDEFINITE); // Repeat indefinitely
        getBgPlayer().play();
    }

    public void stickGrowMusic(){
        String musicName = "src\\main\\resources\\stick.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        mediaPlayer1 = new MediaPlayer(media);
        if (isIsMute()) mediaPlayer1.setMute(true);
        mediaPlayer1.play();
    }

    public void fallMusic(){
        String musicName = "src\\main\\resources\\falling.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        mediaPlayer1 = new MediaPlayer(media);
        mediaPlayer1.setVolume(50);
        if (isIsMute()) mediaPlayer1.setMute(true);
        mediaPlayer1.play();
    }
    public void flipMusic(){
        String musicName = "src\\main\\resources\\flip.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        mediaPlayer1 = new MediaPlayer(media);
        if (isIsMute()) mediaPlayer1.setMute(true);
        mediaPlayer1.play();
    }
    public void nextMusic(){
        String musicName = "src\\main\\resources\\next.mp3";
        Media media = new Media(Paths.get(musicName).toUri().toString());
        mediaPlayer1 = new MediaPlayer(media);
        if (isIsMute()) mediaPlayer1.setMute(true);
        mediaPlayer1.play();
    }
}