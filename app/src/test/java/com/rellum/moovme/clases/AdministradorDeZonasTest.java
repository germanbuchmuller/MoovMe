package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdministradorDeZonasTest {

    @Test
    public void agregarZona() {
        AdministradorDeZonas administradorDeZonas= new AdministradorDeZonas();
        administradorDeZonas.agregarZona("Pilar");

        //Assert
        Zona zonaObtenida =administradorDeZonas.getZona("Pilar");
        assertEquals("Pilar",zonaObtenida.getNombre());
    }

    @Test
    public void agregarTerminal() {
        AdministradorDeZonas administradorDeZonas= new AdministradorDeZonas();
        administradorDeZonas.agregarZona("Pilar");
        administradorDeZonas.agregarTerminal(administradorDeZonas.getZona("Pilar"),"Dirección 1");

        //Assert
        assertEquals("Dirección 1",administradorDeZonas.getZona("Pilar").getTerminalByDireccion("Dirección 1").getDireccion());
        assertEquals(administradorDeZonas.getZona("Pilar").getTerminalByDireccion("Dirección 1"),administradorDeZonas.getTerminal(1));

    }

    @Test
    public void agregarTerminal1() {
        AdministradorDeZonas administradorDeZonas=new AdministradorDeZonas();
        administradorDeZonas.agregarZona("Pilar");
        administradorDeZonas.agregarTerminal("Pilar","Dirección 1");
        administradorDeZonas.agregarTerminal("Pilar","Dirección 2");
        System.out.println(administradorDeZonas.getZona("Pilar").getTerminal(2).getDireccion());

        //Assert
        assertEquals("Dirección 2",administradorDeZonas.getTerminal(3).getDireccion());
    }
}