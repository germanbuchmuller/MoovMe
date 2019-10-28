package com.rellum.moovme.clases;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ZonaTest {

    @Test
    public void agregarTerminal() {
        Zona pilar = new Zona("Pilar");
        pilar.agregarTerminal(0001,"Mariano Acosta 1611");

        //Assert
        assertEquals("Mariano Acosta 1611",pilar.getTerminal(0001).getDireccion());

    }

    @Test
    public void agregarActivosALaTerminal() {
        Zona pilar = new Zona("Pilar");
        pilar.agregarTerminal(0001,"Mariano Acosta 1611");
        ArrayList<Activo>activos = new ArrayList<>();
        TipoDeActivo bicicleta = new TipoDeActivo("Bicicleta");
        activos.add(new Activo(bicicleta,80));
        pilar.agregarActivosALaTerminal(0001,activos);

        //Assert
        assertEquals(activos.get(0),pilar.getTerminal(0001).getActivo(bicicleta));
    }

    @Test
    public void getLastIdTerminal() {
        Zona pilar = new Zona("Pilar");
        pilar.agregarTerminal(0001,"Mariano Acosta 1611");
        pilar.agregarTerminal(0002,"Dirección 1");
        pilar.agregarTerminal(0003,"Dirección 2");
        pilar.agregarTerminal(0004,"Dirección 3");

        pilar.eliminarTerminal(0002);

        //Assert
        assertEquals(0004,pilar.getLastIdTerminal());

    }

    @Test
    public void getTerminalByDireccion() {
        Zona pilar = new Zona("Pilar");
        pilar.agregarTerminal(0001,"Mariano Acosta 1611");
        pilar.agregarTerminal(0002,"Dirección 1");
        pilar.agregarTerminal(0003,"Dirección 2");
        pilar.agregarTerminal(0004,"Dirección 3");
        //Assert
        assertEquals(0002,pilar.getTerminalByDireccion("Dirección 1").getIdTerminal());
    }

    @Test
    public void entregarLoteDeActivos() {
        Zona pilar = new Zona("Pilar");
        pilar.agregarTerminal(0001,"Mariano Acosta 1611");
        pilar.agregarTerminal(0002,"Dirección 1");
        pilar.agregarTerminal(0003,"Dirección 2");
        pilar.agregarTerminal(0004,"Dirección 3");
        TipoDeActivo moto  =new TipoDeActivo("Moto");

        ArrayList<Activo>activosAEntregar = new ArrayList<>();

        for (int i = 0; i <9; i++) {
            activosAEntregar.add(new Activo(moto,50));
        }

        LoteDeActivos loteDeActivos=new LoteDeActivos(00001,activosAEntregar,"Pilar");
        pilar.entregarLoteDeActivos(loteDeActivos);


        //Assert
        assertEquals(3,pilar.getTerminalByDireccion("Mariano Acosta 1611").getCantidadDeActivosDisponibles(moto));
        assertEquals(2,pilar.getTerminalByDireccion("Dirección 1").getCantidadDeActivosDisponibles(moto));
        assertEquals(2,pilar.getTerminalByDireccion("Dirección 2").getCantidadDeActivosDisponibles(moto));
        assertEquals(2,pilar.getTerminalByDireccion("Dirección 3").getCantidadDeActivosDisponibles(moto));


    }

    @Test
    public void getRanking() {
        Zona pilar = new Zona("Pilar");
        Cliente cliente1= new Cliente("cliente1","cliente1","cliente1","cliente1",22);
        Cliente cliente2= new Cliente("cliente2","cliente2","cliente2","cliente2",22);
        Cliente cliente3= new Cliente("cliente3","cliente3","cliente3","cliente3",22);
        pilar.agregarPuntosACliente(cliente1,30);
        pilar.agregarPuntosACliente(cliente2,60);
        pilar.agregarPuntosACliente(cliente3,40);

        //Assert
        System.out.println(pilar.getRanking());
    }
}