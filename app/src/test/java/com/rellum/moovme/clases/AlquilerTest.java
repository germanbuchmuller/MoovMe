package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlquilerTest {

    @Test
    public void calcularTiempoDeAlquiler() {
        Cliente cliente=new Cliente("a","a","a","a",4);
        Integer[]horaEmision=new Integer[2];
        horaEmision[0]=13;
        horaEmision[1]=20;
        Terminal terminal=new Terminal(1,"aa");
        Activo activo=new Activo(new TipoDeActivo("Moto",2),2);
        Zona zona=new Zona("pilar");
        Alquiler alquiler=new Alquiler(cliente,horaEmision,terminal,activo,zona);

        //Assert
        Integer[]horaEntrega=new Integer[2];
        horaEntrega[0]=14;
        horaEntrega[1]=50;
        assertEquals(90,alquiler.calcularTiempoDeAlquiler(horaEntrega));

    }
}