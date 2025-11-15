package player;

public class Song {
    private String title;
    private String artist;
    private String filepath;

    public Song(String title, String artist, String filepath) {
        this.title = title;
        this.artist = artist;
        this.filepath = filepath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getFilepath() {
        return filepath;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}
