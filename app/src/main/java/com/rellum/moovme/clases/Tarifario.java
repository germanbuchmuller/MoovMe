package com.rellum.moovme.clases;
//te dice cuanto vale un activo en una zona
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Tarifario {
    private HashMap<String,HashMap<TipoDeActivo,Double>>lista;
    private HashSet<String>zonas;

    public Tarifario() {
        lista=new HashMap<String, HashMap<TipoDeActivo, Double>>();
        zonas = new HashSet<String>();

    }
//crea un hashmap que tiene el tipo de activo y el valor
    public void agregarTarifa(Activo activo, String zona, double precio){
            if (!lista.keySet().contains(zona)) {
                HashMap<TipoDeActivo, Double> temp = new HashMap<>();
                temp.put(activo.getType(), precio);
                lista.put(zona, temp);
                zonas.add(zona);
            } else {
                if (!lista.get(zona).containsKey(activo.getType())) {
                    HashMap<TipoDeActivo, Double> temp = lista.get(zona);
                    temp.put(activo.getType(), precio);
                    lista.put(zona, temp);
                }else {
                    throw new RuntimeException("Tarifa ya agregada");
                }
            }

    }

    public void actualizarTarifa(Activo activo, String zona, double nuevoPrecio){
        if (lista.keySet().contains(zona)){
            if (lista.get(zona).keySet().contains(activo.getType())){
                lista.get(zona).replace(activo.getType(),nuevoPrecio);
            }else{
                throw new RuntimeException("Activo no encontrado para la zona: "+zona);
            }
        }else{
            throw new RuntimeException("Zona "+zona+" no encontrada");
        }

    }
    //devuelve el precio por mimnuto de activo en una zona
    public double getPrice(Activo activo, String zona){
        if (lista.keySet().contains(zona)){
            if (lista.get(zona).keySet().contains(activo.getType())){
                return lista.get(zona).get(activo.getType());
            }
        }
        throw new RuntimeException("Precio no encontrado");
    }

    public double getPrice(TipoDeActivo tipoDeActivo, String zona){
        if (lista.keySet().contains(zona)){
            if (lista.get(zona).keySet().contains(tipoDeActivo)){
                return lista.get(zona).get(tipoDeActivo);
            }
        }
        throw new RuntimeException("Precio no encontrado");
    }

    public double getPrice(Activo activo, Zona zona){
        if (lista.keySet().contains(zona.getNombre())){
            if (lista.get(zona.getNombre()).keySet().contains(activo.getType())){
                return lista.get(zona.getNombre()).get(activo.getType());
            }
        }
        throw new RuntimeException("Precio no encontrado");
    }

    public double getPrice(TipoDeActivo tipoDeActivo, Zona zona){
        if (lista.keySet().contains(zona.getNombre())){
            if (lista.get(zona.getNombre()).keySet().contains(tipoDeActivo)){
                return lista.get(zona.getNombre()).get(tipoDeActivo);
            }
        }
        throw new RuntimeException("Precio no encontrado");
    }

    public ArrayList<String> getZonasList(){
        ArrayList<String>returnResult=new ArrayList<String >();
        Iterator value = this.zonas.iterator();
        while (value.hasNext()){
            returnResult.add(value.next().toString());
        }
        return returnResult;
    }
}
