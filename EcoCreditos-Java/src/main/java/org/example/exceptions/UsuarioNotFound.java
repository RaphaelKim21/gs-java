package org.example.exceptions;

public class UsuarioNotFound extends Exception{
        public UsuarioNotFound(Long id) {
            super("nao localizado -> id: " + id);
        }
}

