package com.rellum.moovme.clases;

import java.util.ArrayList;

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
        for (int i = 0; i < activos.size(); i++) {
            if (activos.get(i).getType().equals(tipoDeActivo)){
                Activo returnActivo = activos.get(i);
                activos.remove(i);
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
            }
        }
        return result;
    }

    public int getCantidadDeActivosDisponibles(){
        return activos.size();
    }
}
