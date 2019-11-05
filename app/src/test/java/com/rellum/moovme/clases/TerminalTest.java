package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class TerminalTest {

    @Test
    public void agregarActivo() {
        Terminal terminal=new Terminal(0001,"Dirección 1");
        TipoDeActivo moto = new TipoDeActivo("Moto",20);
        Activo activo=new Activo(moto,50);
        terminal.agregarActivo(activo);

        //Assert
        assertEquals(50,terminal.getActivo(moto).getPuntos());
    }

    @Test
    public void getCantidadDeActivosDisponibles(TipoDeActivo tipoDeActivo) {
        Terminal terminal=new Terminal(0001,"Dirección 1");
        TipoDeActivo moto = new TipoDeActivo("Moto",20);
        TipoDeActivo bici = new TipoDeActivo("Bicicleta",20);
        terminal.agregarActivo(new Activo(moto,50));
        terminal.agregarActivo(new Activo(moto,50));
        terminal.agregarActivo(new Activo(bici,50));
        int actual = terminal.getCantidadDeActivosDisponibles();
        //Assert
        assertEquals(2,actual-1);

    }

    @Test
    public void getCantidadDeActivosDisponibles1() {
    }
}