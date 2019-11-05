package com.rellum.moovme.clases;

public class Cliente extends Usuario {

    protected String alias;
    protected int celular;
    private boolean banned;

    public Cliente(String fullname, String alias, String username, String password, int numeroCelular) {
        super(fullname, username, password);
        this.alias=alias;
        this.celular=numeroCelular;
        banned=false;
    }

    public void ban(){
        banned=true;
    }

    public void undoBan(){
        banned=false;
    }

    public boolean isBanned() {
        return banned;
    }

    public String getAlias() {
        return alias;
    }
}
