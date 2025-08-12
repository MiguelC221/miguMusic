package org.miguquis.music.dev_Interface;

public class Album {
    public String albumName;
    public String albumArtist;
    public String albumYear;
    public String albumGenre;

    //Song Metadata
    String name;
    String artist;
    String duration;

    public Album() {
    }

    public Album(String albumName, String albumArtist, String albumYear, String albumGenre, String name, String artist) {
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.albumYear = albumYear;
        this.albumGenre = albumGenre;
        this.name = name;
        this.artist = artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getAlbumYear() {
        return albumYear;
    }

    public void setAlbumYear(String albumYear) {
        this.albumYear = albumYear;
    }

    public String getAlbumGenre() {
        return albumGenre;
    }

    public void setAlbumGenre(String albumGenre) {
        this.albumGenre = albumGenre;
    }
}
