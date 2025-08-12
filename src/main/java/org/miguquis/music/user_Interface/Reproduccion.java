package org.miguquis.music.dev_Interface;

import java.util.List;

public class Reproduccion {
    private List listaReproduccion;
    private List reproducirAleatorio;
    private List reproduccion;

    public Reproduccion() {}

    public Reproduccion(List listaReproduccion, List reproducirAleatorio, List reproduccion) {
        this.listaReproduccion = listaReproduccion;
        this.reproducirAleatorio = reproducirAleatorio;
        this.reproduccion = reproduccion;
    }

    public List getListaReproduccion() {
        return listaReproduccion;
    }

    public void setListaReproduccion(List listaReproduccion) {
        this.listaReproduccion = listaReproduccion;
    }

    public List getReproducirAleatorio() {
        return reproducirAleatorio;
    }

    public void setReproducirAleatorio(List reproducirAleatorio) {
        this.reproducirAleatorio = reproducirAleatorio;
    }

    public List getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(List reproduccion) {
        this.reproduccion = reproduccion;
    }
}
