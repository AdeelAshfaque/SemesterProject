package player;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
    private LinkedList<Song> songs = new LinkedList<>();

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void displaySongs() {
        System.out.println("\n--- Playlist ---");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }
}
