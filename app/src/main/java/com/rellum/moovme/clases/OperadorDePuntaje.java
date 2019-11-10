package com.rellum.moovme.clases;

import com.rellum.moovme.MainActivity;

import java.util.HashMap;

public class OperadorDePuntaje {
    private HashMap<Zona,HashMap<TipoDeActivo,Integer[]>>puntaje;
/*el hashamp puntaje. en cada zona, a cada tipo de activo le corresponde una cantidad de ptos a canjear
para obtener un determinado descuento
   */
    public OperadorDePuntaje() {
        puntaje=new HashMap<>();
    }

    public void agregarPuntaje(Zona zona, TipoDeActivo tipoDeActivo, int puntos, int descuento){
            if (puntaje.containsKey(zona)){
            HashMap<TipoDeActivo,Integer[]>puntajeValue;
            if (puntaje.get(zona)!=null){
               puntajeValue =puntaje.get(zona);
            }else{
                puntajeValue=new HashMap<TipoDeActivo,Integer[]>();
            }
            Integer[]puntosYDescuento=new Integer[2];
            puntosYDescuento[0]=puntos;
            puntosYDescuento[1]=descuento;
            puntajeValue.put(tipoDeActivo,puntosYDescuento);
            puntaje.put(zona,puntajeValue);

        }else{
            HashMap<TipoDeActivo,Integer[]>puntajeValue=new HashMap<TipoDeActivo,Integer[]>();
            Integer[]puntosYDescuento=new Integer[2];
            puntosYDescuento[0]=puntos;
            puntosYDescuento[1]=descuento;
            puntajeValue.put(tipoDeActivo,puntosYDescuento);
            puntaje.put(zona,puntajeValue);
        }




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
        if (puntaje.containsKey(zona)){
            if (puntaje.get(zona).containsKey(tipoDeActivo)){
                return puntaje.get(zona).get(tipoDeActivo);
            }else{
                throw new RuntimeException("Puntaje no establecido para el tipo de activo seleccionado");
            }
        }else   {
            throw new RuntimeException("Zona no encontrada");
        }

    }

    public Integer[] getPuntaje(String zona,TipoDeActivo tipoDeActivo){
        return puntaje.get(MainActivity.administradorDeZonas.getZona(zona)).get(tipoDeActivo);
    }
}
