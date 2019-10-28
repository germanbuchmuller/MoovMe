package com.rellum.moovme.clases;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListaDeUsuariosTest {

    @Test
    public void deleteClienteDeZona() {
        ListaDeUsuarios listaDeUsuarios=new ListaDeUsuarios();
        Cliente cliente= new Cliente("Pablo","Pablito","pablo1234","1234",2323);
        listaDeUsuarios.agregarCliente(cliente);
        listaDeUsuarios.agregarClienteAZona(cliente,"Pilar");

        //Assert
        assertEquals(listaDeUsuarios.getClientesByZona("Pilar").get(0),cliente);
    }
}