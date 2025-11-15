package player;

import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MusicPlayer {
    private Player player;
    private FileInputStream fileInputStream;

    public void playSong(String filepath) {
        try {
            fileInputStream = new FileInputStream(filepath);
            player = new Player(fileInputStream);
            System.out.println("Playing: " + filepath);

            new Thread(() -> {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println("Error during playback: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    public void stopSong() {
        if (player != null) {
            player.close();
            System.out.println("Playback stopped.");
        }
    }
}
