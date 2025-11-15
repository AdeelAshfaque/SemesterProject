package player;

import java.util.Scanner;

public class MainMenu {

    private static Playlist playlist = new Playlist();
    private static MusicPlayer player = new MusicPlayer();
    private static int currentIndex = -1;  // keeps track of the currently playing song

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        playlist.addSong(new Song("Song1", "Eminem", "songs/song1.mp3"));
        playlist.addSong(new Song("Song2", "Artist2", "songs/song2.mp3"));

        while (true) {
            System.out.println("\n=== Music Player Menu ===");
            System.out.println("1. Play Song");
            System.out.println("2. Stop Song");
            System.out.println("3. Show Playlist");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    playlist.displaySongs();
                    System.out.print("Enter song number: ");
                    int songNumber = scanner.nextInt();
                    if (songNumber >= 1 && songNumber <= playlist.getSongs().size()) {
                        currentIndex = songNumber - 1;   // store currently playing index
                        Song selectedSong = playlist.getSongs().get(currentIndex);
                        player.stopSong();
                        player.playSong(selectedSong.getFilepath());

                    } else {
                        System.out.println("Invalid selection.");
                    }
                }
                case 2 -> player.stopSong();
                case 3 -> playlist.displaySongs();
                case 4 -> {
                    if (currentIndex + 1 < playlist.getSongs().size()) {
                        currentIndex++;
                        Song nextSong = playlist.getSongs().get(currentIndex);
                        player.stopSong();

                        player.playSong(nextSong.getFilepath());
                    } else {
                        System.out.println("No next song.");
                    }
                }
                case 5 -> {
                    if (currentIndex - 1 >= 0) {
                        currentIndex--;
                        Song prevSong = playlist.getSongs().get(currentIndex);
                        player.stopSong();

                        player.playSong(prevSong.getFilepath());
                    } else {
                        System.out.println("No previous song.");
                    }
                }
                case 6 -> {
                    player.stopSong();
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
