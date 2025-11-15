package player;

import java.util.ArrayList;
import java.util.List;

public class PlaylistManager {
    private List<Playlist> playlists = new ArrayList<>();
    private Playlist currentPlaylist;

    public void createPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.add(playlist);
        currentPlaylist = playlist;
        System.out.println("Playlist '" + name + "' created and selected.");
    }

    public void deletePlaylist(String name) {
        playlists.removeIf(p -> p.getName().equalsIgnoreCase(name));
        if (currentPlaylist != null && currentPlaylist.getName().equalsIgnoreCase(name)) {
            currentPlaylist = playlists.isEmpty() ? null : playlists.get(0);
        }
        System.out.println("Playlist '" + name + "' deleted.");
    }

    public void selectPlaylist(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                currentPlaylist = p;
                System.out.println("Playlist '" + name + "' selected.");
                return;
            }
        }
        System.out.println("Playlist not found.");
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void listPlaylists() {
        System.out.println("\n--- Playlists ---");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getName());
        }
    }
}
