package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperadorDePuntajeTest {

    @Test
    public void agregarPuntaje() {
        OperadorDePuntaje operadorDePuntaje=new OperadorDePuntaje();
        Zona zona=new Zona("Pilar");
        TipoDeActivo tipoDeActivo=new TipoDeActivo("Bici",3);
        operadorDePuntaje.agregarPuntaje(zona,tipoDeActivo,5,3);

        //Assert
        Integer[]result=operadorDePuntaje.getPuntaje(zona,tipoDeActivo);
        int descuento=result[1];
        assertEquals(3,descuento);
    }
}