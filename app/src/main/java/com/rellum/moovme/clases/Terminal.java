package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Terminal {
    private int idTerminal;
    private String direccion;
    private ArrayList<Activo> activos;

    public Terminal(int idTerminal, String direccion) {
        this.idTerminal = idTerminal;
        this.direccion = direccion;
        this.activos = new ArrayList<Activo>();
    }

    public void agregarActivo(Activo unActivo){
        //le pasas un activo y lo agrega a la lista
        activos.add(unActivo);
    }

    public void agregarActivos(ArrayList<Activo> activosAAgregar){
        for (int i = 0; i < activosAAgregar.size(); i++) {
            activos.add(activosAAgregar.get(i));
        }
    }

    public int getIdTerminal() {
        return idTerminal;
    }

    public Activo getActivo(TipoDeActivo tipoDeActivo){
        //le pasas que tipo de activo queres, y si lo tiene te lo devuelve
        for (int i = 0; i < activos.size(); i++) {
            if (activos.get(i).getType().equals(tipoDeActivo)){
                Activo returnActivo = activos.get(i);
                activos.remove(i);
                //una vez que sale la moto, se la saca de la lista
                return returnActivo;
            }
        }
        throw new RuntimeException("No se encuentra el activo pedido en la terminal.");
    }

    public ArrayList<Activo> getActivos() {
        return activos;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCantidadDeActivosDisponibles(TipoDeActivo tipoDeActivo){
        int result=0;
        for (int i = 0; i < activos.size(); i++) {
            if (activos.get(i).getType().equals(tipoDeActivo)){
                result+=1;
                //cuenta cuantos activos hay de un tipo
            }
        }
        return result;
    }

    public int getCantidadDeActivosDisponibles(){
        return activos.size();
    }

    public ArrayList<TipoDeActivo> getTiposDeActivoDisponible(){
        HashSet<TipoDeActivo>tiposDeActivo=new HashSet<>();
        for (Activo activo : activos) {
            tiposDeActivo.add(activo.getType());
        }
        ArrayList<TipoDeActivo>returnResult=new ArrayList<>();
        Iterator iterator=tiposDeActivo.iterator();
        while (iterator.hasNext()){
            returnResult.add((TipoDeActivo) iterator.next());
        }

        return returnResult;
    }
}
