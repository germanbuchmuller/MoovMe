package com.rellum.moovme.clases;

public class TipoDeActivo {
    private String tipoDeActivo="";
    private int puntos;

    public TipoDeActivo(String tipoDeActivo, int puntos) {
        this.tipoDeActivo = tipoDeActivo;
        this.puntos = puntos;
    }

    public String toString() {
        return tipoDeActivo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
