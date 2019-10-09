package com.rellum.moovme.clases;

public class Cliente extends Usuario {

    protected String alias;
    protected int celular;

    public Cliente(String fullname, String alias, String username, String password, int numeroCelular) {
        super(fullname, username, password);
        this.alias=alias;
        this.celular=numeroCelular;
    }
}
