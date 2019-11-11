package com.rellum.moovme.clases;

import java.util.ArrayList;

public class LoteDeActivos {
    private int codigoDeLote;
    private ArrayList<Activo>activos;


    public LoteDeActivos(int codigoDeLote, ArrayList<Activo> activos) {
        this.codigoDeLote = codigoDeLote;
        this.activos = activos;
    }

    public int getCodigoDeLote() {
        return codigoDeLote;
    }

    public ArrayList<Activo> getActivos() {
        return activos;
    }

    public ArrayList<Activo> getActivosPorCantidad(int cantidad){
        if (cantidad<=activos.size()) {
            ArrayList<Activo> result = new ArrayList<>();
            for (int i = 0; i < cantidad; i++) {
                result.add(activos.get(i));
            }
            for (int i = 0; i < cantidad; i++) {
                activos.remove(i);
            }
            return result;
        }else{
            throw new RuntimeException("Cantidad de activos insuficiente");
        }
    }
}
