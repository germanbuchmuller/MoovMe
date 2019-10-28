package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Zona {
    private String nombre;
    private HashMap<Integer,Terminal>terminales;
    private ArrayList<Integer>terminalesId;
    private HashMap<String, Cliente> clientesEnZona;
    private HashMap<Cliente,Integer>clientesPuntajeTotal;
    private HashMap<Cliente,Integer>clientesPuntajeParcial;

    public Zona(String nombre) {
        this.nombre = nombre;
        this.terminales = new HashMap<Integer, Terminal>();
        this.terminalesId=new ArrayList<Integer>();
        this.clientesEnZona = new HashMap<String, Cliente>();
        this.clientesPuntajeTotal= new HashMap<>();
        this.clientesPuntajeParcial= new HashMap<>();
    }

    public void agregarTerminal(int idTerminal, String direccion){
        if (!terminales.containsKey(idTerminal)){
            terminales.put(idTerminal,new Terminal(idTerminal,direccion));
            terminalesId.add(idTerminal);
        }else{
            throw new RuntimeException("Terminal ya existente");
        }
    }

    public void agregarActivosALaTerminal(Terminal terminal, ArrayList<Activo>activos){
        if (terminales.containsValue(terminal)){
            for (int i = 0; i < activos.size(); i++) {
                terminal.agregarActivo(activos.get(i));
            }
            terminales.replace(terminal.getIdTerminal(),terminal);
        }else{
            throw new RuntimeException("No existe dicha terminal en esta zona");
        }
    }

    public void agregarActivosALaTerminal(int idTerminal, ArrayList<Activo>activos){
        if (terminales.containsKey(idTerminal)){
            Terminal terminal=terminales.get(idTerminal);
            for (int i = 0; i < activos.size(); i++) {
                terminal.agregarActivo(activos.get(i));
            }
            terminales.replace(idTerminal,terminal);
        }else{
            throw new RuntimeException("No existe dicha terminal en esta zona");
        }
    }

    public Terminal getTerminal(int idTerminal){
        if (terminales.containsKey(idTerminal)){
            return terminales.get(idTerminal);
        }else {
            throw new RuntimeException("Terminal no encontrada");
        }
    }

    public void eliminarTerminal(int idTerminal){
        if (terminales.containsKey(idTerminal)){
            terminales.remove(idTerminal);
            terminalesId.remove(idTerminal);
        }else{
            throw new RuntimeException("Terminal no encontrada");
        }
    }

    public void eliminarTerminal(Terminal terminal){
        if (terminales.containsValue(terminal)){
            terminales.remove(terminal.getIdTerminal());
            terminalesId.remove(terminal.getIdTerminal());
        }else{
            throw new RuntimeException("Terminal no encontrada");
        }
    }

    public int getLastIdTerminal(){
        int result=0;
        Iterator<Terminal> iterator = terminales.values().iterator();

        while (iterator.hasNext()) {
            Terminal terminal = iterator.next();
            if (terminal.getIdTerminal()>result){
                result=terminal.getIdTerminal();
            }
        }
        return result;
    }

    public ArrayList<Terminal> getTerminales(){
        ArrayList<Terminal>result=new ArrayList<Terminal>();
        Iterator<Terminal> iterator = terminales.values().iterator();

        while (iterator.hasNext()) {
            Terminal terminal = iterator.next();
           result.add(terminal);
        }
        return result;
    }

    public ArrayList<String> getTerminalesList(){
        ArrayList<String>result=new ArrayList<String>();
        Iterator<Terminal> iterator = terminales.values().iterator();

        while (iterator.hasNext()) {
            Terminal terminal = iterator.next();
            result.add(terminal.getDireccion());
        }
        return result;
    }

    public Terminal getTerminalByDireccion(String direccion){
        ArrayList<Terminal> terminales = getTerminales();
        for (int i = 0; i < terminales.size(); i++) {
            if (terminales.get(i).getDireccion().equals(direccion)){
                return terminales.get(i);
            }
        }
        throw new RuntimeException("Terminal no encontrada");
    }

    public String getNombre(){
        return nombre;
    }

    public void entregarLoteDeActivos(LoteDeActivos loteDeActivos) {
        if (terminales.size()>0) {
            while (loteDeActivos.getActivos().size() > 0) {
                for (int i = 0; i < terminales.size(); i++) {
                    if (loteDeActivos.getActivos().size() > 0) {
                        terminales.get(terminalesId.get(i)).agregarActivos(loteDeActivos.getActivosPorCantidad(1));
                    } else {
                        break;
                    }
                }
            }
        }else{
            throw new RuntimeException("La zona no contiene terminales");
        }
    }

    public boolean containsTerminal(Terminal terminal){
        if (terminales.containsValue(terminal)){
            return true;
        }else {
            return false;
        }
    }

    public boolean containsTerminal(String direccionTerminal){
        try {
            if (terminales.containsValue(getTerminalByDireccion(direccionTerminal))){
                return true;
            }else {
                return false;
            }
        }catch (RuntimeException exception){
            return false;
        }

    }

    public void agregarPuntosACliente(Cliente cliente, int puntos){
        if (clientesPuntajeTotal.containsKey(cliente)) {
            int puntosTotales = clientesPuntajeTotal.get(cliente);
            int puntosParciales = clientesPuntajeParcial.get(cliente);
            puntosParciales += puntos;
            puntosTotales += puntos;
            clientesPuntajeParcial.put(cliente, puntosParciales);
            clientesPuntajeTotal.put(cliente, puntosTotales);
        }else{
            clientesPuntajeParcial.put(cliente, puntos);
            clientesPuntajeTotal.put(cliente, puntos);
        }
    }

    public void consumirPuntosAlCliente(Cliente cliente,int puntos){
        if (clientesPuntajeParcial.containsKey(cliente)) {
            int puntosParciales = clientesPuntajeParcial.get(cliente);
            if (puntosParciales>=puntos){
                puntosParciales -= puntos;
                clientesPuntajeParcial.put(cliente, puntosParciales);
            }else{
                throw new RuntimeException("Puntos insuficientes");
            }

        }else{
            throw new RuntimeException("Usuario no registrado en esta zona");
        }
    }

    public HashMap<Cliente, Integer> getRanking(){
        List<Map.Entry<Cliente, Integer> > lista = new LinkedList<Map.Entry<Cliente, Integer> >(clientesPuntajeTotal.entrySet());
        Collections.sort(lista, new Comparator<Map.Entry<Cliente, Integer> >() {
            public int compare(Map.Entry<Cliente, Integer> puntaje1, Map.Entry<Cliente, Integer> puntaje2)
            {
                return (puntaje2.getValue()).compareTo(puntaje1.getValue());
            }
        });

        HashMap<Cliente, Integer> result = new LinkedHashMap<Cliente, Integer>();
        for (Map.Entry<Cliente, Integer> entry : lista) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


}
