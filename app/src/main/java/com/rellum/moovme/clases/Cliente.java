package com.rellum.moovme.clases;
//otro de los tipos de ususario
public class Cliente extends Usuario {
// tiene un alias, un celular y la posibilidad de estar baneado
    protected String alias;
    protected int celular;
    private boolean banned;

    public Cliente(String fullname, String alias, String username, String password, int numeroCelular) {
        super(fullname, username, password);
        //llama al constructor de usuario
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
