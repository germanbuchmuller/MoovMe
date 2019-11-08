package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperadorDePuntajeTest {

    @Test
    public void agregarPuntaje() {
        OperadorDePuntaje operadorDePuntaje=new OperadorDePuntaje();
        Zona zona=new Zona("Pilar");
        TipoDeActivo tipoDeActivo=new TipoDeActivo("Bici",3);
        TipoDeActivo tipoDeActivo2=new TipoDeActivo("Moto",4);
        operadorDePuntaje.agregarPuntaje(zona,tipoDeActivo,5,3);
        operadorDePuntaje.agregarPuntaje(zona,tipoDeActivo2,15,30);

        //Assert
        Integer[]result1=operadorDePuntaje.getPuntaje(zona,tipoDeActivo);
        int descuento=result1[1];
        Integer[]result2=operadorDePuntaje.getPuntaje(zona,tipoDeActivo2);
        int descuento2=result2[1];
        assertEquals(3,descuento);
        assertEquals(30,descuento2);
    }
}