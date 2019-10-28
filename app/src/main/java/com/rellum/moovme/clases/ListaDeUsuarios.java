package com.rellum.moovme.clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ListaDeUsuarios {
    private HashMap<String,Admin> admins;
    private HashMap<String,Cliente>clientes;
    private HashMap<String, ArrayList<Cliente>>listaDeClientesPorZona;


    public ListaDeUsuarios() {
        admins=new HashMap<String,Admin>();
        clientes=new HashMap<String, Cliente>();
        listaDeClientesPorZona=new HashMap<>();
        admins.put("admin",new Admin("Default Admin","admin","admin"));

    }

    public void agregarCliente(String fullname, String alias, String username, String password, int phoneNumber ){
        if (!clientes.keySet().contains(username)&&!admins.keySet().contains(username)){
            clientes.put(username,new Cliente(fullname,alias,username,password,phoneNumber));
        }else{
            throw new RuntimeException("Usuario ya registrado");
        }
    }

    public void agregarCliente(Cliente cliente){
        if (!clientes.containsValue(cliente)) {
            clientes.put(cliente.username, cliente);
        }else{
            throw new RuntimeException("Usuario ya registrado");
        }
    }

    public void agregarAdmin(String fullname, String username, String password){
        if (!clientes.keySet().contains(username)&&!admins.keySet().contains(username)){
            admins.put(username,new Admin(fullname,username,password));
        }else{
            throw new RuntimeException("Usuario ya registrado");
        }
    }

    public void agregarAdmin(Admin admin){
        if (!admins.containsValue(admin)) {
            admins.put(admin.username, admin);
        }else{
            throw new RuntimeException("Usuario ya registrado");
        }
    }

    public Usuario getUserByUsername(String username){
        if(clientes.keySet().contains(username)){
            return clientes.get(username);
        }else if(admins.keySet().contains(username)){
            return admins.get(username);
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void deleteUserByUsername(String username){
        if(clientes.keySet().contains(username)){
            clientes.remove(username);
        }else if(admins.keySet().contains(username)){
            admins.remove(username);
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void agregarClienteAZona(Cliente cliente, Zona zona){
        if (listaDeClientesPorZona.containsKey(zona.getNombre())){
            ArrayList<Cliente>clientesEnZona = listaDeClientesPorZona.get(zona.getNombre());
            clientesEnZona.add(cliente);
            listaDeClientesPorZona.put(zona.getNombre(),clientesEnZona);
        }else{
            ArrayList<Cliente>clientesEnZona =new ArrayList<>();
            clientesEnZona.add(cliente);
            listaDeClientesPorZona.put(zona.getNombre(),clientesEnZona);
        }
    }

    public void agregarClienteAZona(Cliente cliente, String zona){
        if (listaDeClientesPorZona.containsKey(zona)){
            ArrayList<Cliente>clientesEnZona = listaDeClientesPorZona.get(zona);
            clientesEnZona.add(cliente);
            listaDeClientesPorZona.put(zona,clientesEnZona);
        }else{
            ArrayList<Cliente>clientesEnZona =new ArrayList<>();
            clientesEnZona.add(cliente);
            listaDeClientesPorZona.put(zona,clientesEnZona);
        }
    }

    public void deleteClienteDeZona(Cliente cliente){
        Iterator<String> iterator = listaDeClientesPorZona.keySet().iterator();


        while (iterator.hasNext()) {
            String zona=iterator.next();
            ArrayList<Cliente>listaClientes=listaDeClientesPorZona.get(zona);
            if (listaClientes.contains(cliente)){
                listaClientes.remove(cliente);
            }
            listaDeClientesPorZona.put(zona,listaClientes);
        }
    }

    public ArrayList<Cliente> getClientesByZona(String zona){
        if (listaDeClientesPorZona.containsKey(zona)){
            return listaDeClientesPorZona.get(zona);
        }else
            throw new RuntimeException("Zona no encontrada");
    }

    public ArrayList<Cliente> getClientesByZona(Zona zona){
        if (listaDeClientesPorZona.containsKey(zona.getNombre())){
            return listaDeClientesPorZona.get(zona.getNombre());
        }else
            throw new RuntimeException("Zona no encontrada");
    }
}
