package org.example.service;

public class UsuarioServiceFactory {

    private UsuarioServiceFactory() {
        throw new UnsupportedOperationException("classe factory");
    }

    public static UsuarioService create(){
        return new UsuarioServiceImp();
    }
}
