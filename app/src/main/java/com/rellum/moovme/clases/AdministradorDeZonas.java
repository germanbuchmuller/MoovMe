package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class AdministradorDeZonas {
    private HashMap<String,Zona>zonas;
    private HashMap<Integer,Terminal>terminales;
    private int lastIdTerminal;
    private int lastIdLote;

    public AdministradorDeZonas() {
        zonas=new HashMap<>();
        terminales=new HashMap<>();
        lastIdLote=1;
        lastIdTerminal=1;
    }

    public void agregarZona(String nombreDeZona){
        if (!zonas.containsKey(nombreDeZona)){
            zonas.put(nombreDeZona,new Zona(nombreDeZona));
        }else{
            throw new RuntimeException("Zona ya existente");
        }
    }

    public Zona getZona(String nombreDeZona){
        if (zonas.containsKey(nombreDeZona)){
            return zonas.get(nombreDeZona);
        }else{
            throw new RuntimeException("Zona no encontrada");
        }
    }

    public void agregarTerminal(Zona zona,String direccion){
        if (zonas.containsValue(zona)){
            if (zona.containsTerminal(direccion)==false){
                lastIdTerminal+=1;
                zona.agregarTerminal(lastIdTerminal,direccion);
                terminales.put(lastIdTerminal,zona.getTerminal(lastIdTerminal));
            }else{
                throw new RuntimeException("Terminal ya existente");
            }
        }else{
            throw new RuntimeException("Zona no existente");
        }
    }

    public void agregarTerminal(String zona,String direccion){
        if (zonas.containsKey(zona)){

            if (zonas.get(zona).containsTerminal(direccion)==false){
                lastIdTerminal+=1;
                zonas.get(zona).agregarTerminal(lastIdTerminal,direccion);
                terminales.put(lastIdTerminal,zonas.get(zona).getTerminal(lastIdTerminal));
            }else{
                throw new RuntimeException("Terminal ya existente");
            }

        }else{
            throw new RuntimeException("Zona no existente");
        }
    }

    public Terminal getTerminal(int idTerminal){
        if (terminales.containsKey(idTerminal)){
            return terminales.get(idTerminal);
        }else{
            throw new RuntimeException("Terminal no encontrada");
        }
    }

    public void entregarLoteDeActivos(LoteDeActivos loteDeActivos, Zona zona){
        lastIdLote=loteDeActivos.getCodigoDeLote();
        zona.entregarLoteDeActivos(loteDeActivos);
    }

    public void entregarLoteDeActivos(LoteDeActivos loteDeActivos, String zona){
        zonas.get(zona).entregarLoteDeActivos(loteDeActivos);
    }

    public ArrayList<String> getZonasListToString(){
        ArrayList<String>result=new ArrayList<>();
        Iterator<Zona> iterator = zonas.values().iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next().getNombre());
        }
        Collections.sort(result);
        return result;
    }

    public ArrayList<String> getDireccionTerminalesListByZona(String nombreZona){
        if (zonas.containsKey(nombreZona)){
            Zona zona = zonas.get(nombreZona);
            return zona.getTerminalesList();
        }else{
            throw new RuntimeException("Zona no encontrada");
        }
    }

    public ArrayList<String> getDireccionTerminalesListByZona(Zona zona){
        if (zonas.containsValue(zona)){
            return zona.getTerminalesList();
        }else{
            throw new RuntimeException("Zona no encontrada");
        }
    }

    public int getLastIdLote() {
        return lastIdLote;
    }
}
