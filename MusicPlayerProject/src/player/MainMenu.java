package player;

import java.io.File;
import java.util.*;

public class MainMenu {

    private static MusicPlayer player = new MusicPlayer();
    private static List<Playlist> playlists = new ArrayList<>();
    private static Playlist currentPlaylist = new Playlist("Default Playlist");
    private static int currentIndex = -1;

    private static Stack<Song> historyStack = new Stack<>();
    private static Queue<Song> nextQueue = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File folder = new File("songs");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".mp3"));
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                currentPlaylist.addSong(new Song(file.getName(), "Unknown", file.getPath()));
            }
        }
        playlists.add(currentPlaylist);

        while (true) {
            System.out.println("\n=== Music Player Menu ===");
            System.out.println("1. Play Song");
            System.out.println("2. Stop Song");
            System.out.println("3. Show Current Playlist");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Create New Playlist");
            System.out.println("7. Add Song to Playlist");
            System.out.println("8. Show All Playlists");
            System.out.println("9. Switch Playlist");
            System.out.println("10. Remove Song from Playlist");
            System.out.println("11. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> playSong(scanner);
                case 2 -> player.stopSong();
                case 3 -> currentPlaylist.displaySongs();
                case 4 -> playNext();
                case 5 -> playPrevious();
                case 6 -> createPlaylist(scanner);
                case 7 -> addSongToPlaylist(scanner);
                case 8 -> showAllPlaylists();
                case 9 -> switchPlaylist(scanner);
                case 10 -> removeSongFromPlaylist(scanner);
                case 11 -> {
                    player.stopSong();
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void switchPlaylist(Scanner scanner) {
        System.out.println("\n--- Available Playlists ---");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getName());
        }
        System.out.print("Select playlist number to switch: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice >= 1 && choice <= playlists.size()) {
            currentPlaylist = playlists.get(choice - 1);
            currentIndex = -1;
            historyStack.clear();
            nextQueue.clear();
            System.out.println("Switched to playlist: " + currentPlaylist.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void playSong(Scanner scanner) {
        currentPlaylist.displaySongs();
        System.out.print("Enter song number: ");
        int songNumber = scanner.nextInt();
        if (songNumber >= 1 && songNumber <= currentPlaylist.getSongs().size()) {
            if (currentIndex >= 0) historyStack.push(currentPlaylist.getSongs().get(currentIndex));
            currentIndex = songNumber - 1;
            Song selectedSong = currentPlaylist.getSongs().get(currentIndex);
            player.playSong(selectedSong.getFilepath());

            nextQueue.clear();
            for (int i = currentIndex + 1; i < currentPlaylist.getSongs().size(); i++) {
                nextQueue.add(currentPlaylist.getSongs().get(i));
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void playNext() {
        if (!nextQueue.isEmpty()) {
            if (currentIndex >= 0) historyStack.push(currentPlaylist.getSongs().get(currentIndex));
            Song nextSong = nextQueue.poll();
            currentIndex = currentPlaylist.getSongs().indexOf(nextSong);
            player.playSong(nextSong.getFilepath());
        } else {
            System.out.println("No next song.");
        }
    }

    private static void playPrevious() {
        if (!historyStack.isEmpty()) {
            Song prevSong = historyStack.pop();
            currentIndex = currentPlaylist.getSongs().indexOf(prevSong);
            player.playSong(prevSong.getFilepath());

            nextQueue.clear();
            for (int i = currentIndex + 1; i < currentPlaylist.getSongs().size(); i++) {
                nextQueue.add(currentPlaylist.getSongs().get(i));
            }
        } else {
            System.out.println("No previous song.");
        }
    }

    private static void createPlaylist(Scanner scanner) {
        System.out.print("Enter new playlist name: ");
        String name = scanner.nextLine();
        Playlist newPlaylist = new Playlist(name);
        playlists.add(newPlaylist);
        currentPlaylist = newPlaylist;
        currentIndex = -1;
        historyStack.clear();
        nextQueue.clear();
        System.out.println("New playlist created and selected: " + name);
    }

    private static void addSongToPlaylist(Scanner scanner) {
        File folder = new File("songs");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".mp3"));
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + ". " + files[i].getName());
            }
            System.out.print("Enter song number to add: ");
            int songNumber = scanner.nextInt();
            scanner.nextLine();
            if (songNumber >= 1 && songNumber <= files.length) {
                File selectedFile = files[songNumber - 1];
                currentPlaylist.addSong(new Song(selectedFile.getName(), "Unknown", selectedFile.getPath()));
                System.out.println("Song added to current playlist.");
            } else {
                System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("No songs found in 'songs' folder.");
        }
    }

    private static void removeSongFromPlaylist(Scanner scanner) {
        currentPlaylist.displaySongs();
        System.out.print("Enter song number to remove: ");
        int songNumber = scanner.nextInt();
        scanner.nextLine();
        if (songNumber >= 1 && songNumber <= currentPlaylist.getSongs().size()) {
            Song removed = currentPlaylist.getSongs().get(songNumber - 1);
            currentPlaylist.removeSong(removed);
            System.out.println("Removed: " + removed);
            if (currentIndex == songNumber - 1) {
                player.stopSong();
                currentIndex = -1;
            } else if (currentIndex > songNumber - 1) {
                currentIndex--;
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static void showAllPlaylists() {
        System.out.println("\n--- All Playlists ---");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getName() + " (" + playlists.get(i).getSongs().size() + " songs)");
        }
    }
}
