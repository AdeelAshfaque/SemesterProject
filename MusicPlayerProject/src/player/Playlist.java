package player;

import java.util.LinkedList;
import java.util.List;

public class Playlist {
    private String name;
    private LinkedList<Song> songs = new LinkedList<>();

    public Playlist() {
        this.name = "Default Playlist";
    }

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
        System.out.println("\n--- Playlist: " + name + " ---");
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ". " + songs.get(i));
        }
    }
}
