package com.rellum.moovme.clases;

public abstract class Usuario {
    protected String fullname;
    protected String username;
    protected String password;
    private Alquiler alquiler;

    public Usuario(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        alquiler=null;
    }

    public Alquiler getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
