package com.rellum.moovme.clases;

import com.rellum.moovme.clases.Activo;
import com.rellum.moovme.clases.Tarifario;
import com.rellum.moovme.clases.TipoDeActivo;

import org.junit.Test;

import static org.junit.Assert.*;

public class TarifarioTest {

    @Test
    public void agregarTarifa() {
        Activo moto = new Activo(new TipoDeActivo("Moto"),60);
        Tarifario tarifario= new Tarifario();
        tarifario.agregarTarifa(moto,"pilar",40);
        assertEquals(tarifario.getPrice(moto,"pilar"),40);
    }
}