package player;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {
    private Player player;
    private FileInputStream fileInputStream;

    public void playsong(String filepath){
        try{
            fileInputStream = new FileInputStream(filepath);
            player = new Player(fileInputStream);
            System.out.println("Playing: " + filepath);

            new Thread(()->{
                try{
                    player.play();
                }catch (Exception e){
                    System.out.println("Error during playback: " + e.getMessage());
                }
            }).start();
        }catch (FileNotFoundException e){
            System.out.println("File not found: " + filepath);
        }catch (Exception e){
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    public void stopSong(){
        if (player != null){
            player.close();
            System.out.println("PlayBack stopped.");
        }
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("1. Play Song");
            System.out.println("2. Stop Song");
            System.out.println("3. Exit");
            System.out.println("Choose: ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    player.playSong("songs/");
                    break;

                case 2:
                    player.stopSong();
                    break;

                case 3:
                    player.stopSong();
                    System.out.println("GoodBye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
