package org.example.dao;

public class UsuarioDaoFactory {
    private UsuarioDaoFactory() {
    }

    public static UsuarioDao create(){
        return new UsuarioDaoImp ();
    }
}
