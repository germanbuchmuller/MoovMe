package com.rellum.moovme.clases;
//tienen tipos de activo ej:"moto"
public  class Activo {
    private TipoDeActivo tipoDeActivo;

    public Activo(TipoDeActivo tipoDeActivo) {
        this.tipoDeActivo = tipoDeActivo;

    }

    public TipoDeActivo getType() {
        return tipoDeActivo;
    }

    public int getPuntos() {
        return tipoDeActivo.getPuntos();
    //devuelve la cantidad de puntos que da cada activo
    }
}
