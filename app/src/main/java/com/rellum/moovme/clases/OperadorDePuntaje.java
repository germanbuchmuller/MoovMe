package com.rellum.moovme.clases;

import com.rellum.moovme.MainActivity;

import java.util.HashMap;

public class OperadorDePuntaje {
    private HashMap<Zona,HashMap<TipoDeActivo,Integer[]>>puntaje;

    public OperadorDePuntaje() {
        puntaje=new HashMap<>();
    }

    public void agregarPuntaje(Zona zona, TipoDeActivo tipoDeActivo, int puntos, int descuento){
        HashMap<TipoDeActivo,Integer[]>puntajeValue=new HashMap<>();
        Integer[]puntosYDescuento=new Integer[2];
        puntosYDescuento[0]=puntos;
        puntosYDescuento[1]=descuento;
        puntajeValue.put(tipoDeActivo,puntosYDescuento);
        puntaje.put(zona,puntajeValue);
    }

    public void agregarPuntaje(String zona, TipoDeActivo tipoDeActivo, int puntos, int descuento){
        HashMap<TipoDeActivo,Integer[]>puntajeValue=new HashMap<>();
        Integer[]puntosYDescuento=new Integer[2];
        puntosYDescuento[0]=puntos;
        puntosYDescuento[1]=descuento;
        puntajeValue.put(tipoDeActivo,puntosYDescuento);
        puntaje.put(MainActivity.administradorDeZonas.getZona(zona),puntajeValue);
    }

    public Integer[] getPuntaje(Zona zona,TipoDeActivo tipoDeActivo){
        return puntaje.get(zona).get(tipoDeActivo);
    }

    public Integer[] getPuntaje(String zona,TipoDeActivo tipoDeActivo){
        return puntaje.get(MainActivity.administradorDeZonas.getZona(zona)).get(tipoDeActivo);
    }
}
