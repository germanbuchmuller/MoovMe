package com.rellum.moovme.clases;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TarifarioTest {

    @Test
    public void agregarTarifa() {
        Activo moto = new Activo(new TipoDeActivo("Moto",20),60);
        Tarifario tarifario= new Tarifario();
        tarifario.agregarTarifa(moto,"pilar",40);
        assertEquals(tarifario.getPrice(moto,"pilar"),40);
    }
}