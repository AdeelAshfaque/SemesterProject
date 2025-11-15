package player;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MusicPlayer {
    private Player player;
    private FileInputStream fileInputStream;
    private boolean isPlaying = false;

    public void playSong(String filepath) {
        stopSongIfPlaying();
        try {
            fileInputStream = new FileInputStream(filepath);
            player = new Player(fileInputStream);
            isPlaying = true;
            System.out.println("Playing: " + filepath);

            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println("Error during playback: " + e.getMessage());
                } finally {
                    isPlaying = false;
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Error playing song: " + e.getMessage());
            isPlaying = false;
        }
    }

    public void stopSong() {
        if (player != null) {
            player.close();
            player = null;
            System.out.println("Playback stopped.");
            isPlaying = false;
        }
    }

    public void stopSongIfPlaying() {
        if (isPlaying) {
            stopSong();
        }
    }
}
