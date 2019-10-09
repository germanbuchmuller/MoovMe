package com.rellum.moovme.clases;

public abstract class Usuario {
    protected String fullname;
    protected String username;
    protected String password;

    public Usuario(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
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
