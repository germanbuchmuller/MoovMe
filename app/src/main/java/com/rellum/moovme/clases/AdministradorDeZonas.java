package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class AdministradorDeZonas {
    private HashMap<String,Zona>zonas;
    private HashMap<Integer,Terminal>terminales;
    private HashMap<String,Terminal>terminalesPorDireccion;
    private int lastIdTerminal;
    private int lastIdLote;

    public AdministradorDeZonas() {
        zonas=new HashMap<>();
        terminales=new HashMap<>();
        lastIdLote=1;
        lastIdTerminal=1;
        terminalesPorDireccion=new HashMap<>();
    }

    public void agregarZona(String nombreDeZona){
        if (!zonas.containsKey(nombreDeZona)){
            zonas.put(nombreDeZona,new Zona(nombreDeZona));
        }else{
            throw new RuntimeException("Zona ya existente");
        }
    }

    public void eliminarZona(Zona zona){
        zonas.remove(zona.getNombre());
    }

    public void eliminarZona(String zona){
        zonas.remove(zona);
    }

    public Zona getZona(String nombreDeZona){
        if (zonas.containsKey(nombreDeZona)){
            return zonas.get(nombreDeZona);
        }else{
            throw new RuntimeException("Zona no encontrada");
        }
    }
//le pasas una zona y una direccion y lo va agregando a la lista
    public void agregarTerminal(Zona zona,String direccion){
        if (zonas.containsValue(zona)){
            if (zona.containsTerminal(direccion)==false){
                lastIdTerminal+=1;
                // va haciendo que cada terminal tenga el id uno mas grande que el anterior
                zona.agregarTerminal(lastIdTerminal,direccion);
                terminales.put(lastIdTerminal,zona.getTerminal(lastIdTerminal));
                terminalesPorDireccion.put(zona.getTerminal(lastIdTerminal).getDireccion(),zona.getTerminal((lastIdTerminal)));
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
                terminalesPorDireccion.put(zonas.get(zona).getTerminal(lastIdTerminal).getDireccion(),zonas.get(zona).getTerminal(lastIdTerminal));
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

    public Terminal getTerminal(String direccion){
        if (terminalesPorDireccion.containsKey(direccion)){
            return terminalesPorDireccion.get(direccion);
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
// devuelve las zonas en una lista de strings, para la aplicacion
    public ArrayList<String> getZonasListToString(){
        ArrayList<String>result=new ArrayList<>();
        Iterator<Zona> iterator = zonas.values().iterator();

        while (iterator.hasNext()) {
            result.add(iterator.next().getNombre());
        }
        Collections.sort(result);
        return result;
    }
//le pasas una zona y te devuelve la lista de las direcciones de las terminales
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
//mete el cliente que le pasas a la zona que le pasas, y sacan al cliente de las otras zonas donde estaba
    public void ingresarClienteAZona(Cliente cliente, Zona zona){
        Iterator iterator = zonas.values().iterator();
        while (iterator.hasNext()){
            Zona zona2=(Zona)iterator.next();
            zona2.eliminarClienteDeZona(cliente);
            //esto saca a los clientes de las otras zonas
        }
        zona.agregarClienteAZona(cliente);
        //despues de eliminarlo lo mete a la zona que le pedis
    }

    public void ingresarClienteAZona(Cliente cliente, String zona){
        Iterator iterator = zonas.values().iterator();
        while (iterator.hasNext()){
            Zona zona2=(Zona)iterator.next();
            zona2.eliminarClienteDeZona(cliente);
        }

        zonas.get(zona).agregarClienteAZona(cliente);
    }

    public HashMap<Cliente,Integer> getRanking(Zona zona){
        return zona.getRanking();
    }
}
