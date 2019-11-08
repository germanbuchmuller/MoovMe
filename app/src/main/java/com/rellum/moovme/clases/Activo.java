package com.rellum.moovme.clases;
//tienen tipos de activo ej:"moto"
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
        return tipoDeActivo.getPuntos();
    //devuelve la cantidad de puntos que da cada activo
    }
}
