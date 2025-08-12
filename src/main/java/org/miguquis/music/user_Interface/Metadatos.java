package org.miguquis.music.dev_Interface;

import org.json.JSONObject;

public class Metadatos implements Format{
    public String titulo;
    public String artista;
    public String album;
    public String genero;
    public String ano;

    public String ruta;

    public Metadatos(){
    }

    public Metadatos(String titulo, String artista, String album, String genero, String ano, String ruta) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.ano = ano;
        this.ruta = ruta;
    }

    public Metadatos(JSONObject json){
        this(
                json.getString("titulo"),
                json.getString("artista"),
                json.getString("album"),
                json.getString("genero"),
                json.getString("ano"),
                json.getString("ruta")
        );
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Metadatos{");
        sb.append("titulo=").append(titulo);
        sb.append("artista=").append(artista);
        sb.append("album=").append(album);
        sb.append("genero=").append(genero);
        sb.append("ano=").append(ano);
        sb.append("ruta=").append(ruta);
        sb.append("}");
        return sb.toString();
    }
    @Override
    public JSONObject toJSONObject(){
        return new JSONObject(this);
    }
}
