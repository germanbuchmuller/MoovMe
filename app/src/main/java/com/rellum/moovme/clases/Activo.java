package com.rellum.moovme.clases;

public  class Activo {
    private TipoDeActivo tipoDeActivo;
    private int puntos;



    public Activo(TipoDeActivo tipoDeActivo, int puntos) {
        this.tipoDeActivo = tipoDeActivo;
        this.puntos=puntos;
    }

    public void actualizarPuntaje(int puntosNuevos){
        this.puntos=puntosNuevos;
    }

    public TipoDeActivo getType() {
        return tipoDeActivo;
    }

    public int getPuntos() {
        return puntos;
    }
}
