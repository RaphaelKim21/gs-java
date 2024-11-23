package org.example.service;

import org.example.exceptions.UsuarioInvalid;
import org.example.exceptions.UsuarioNotFound;
import org.example.exceptions.UsuarioNotSavedException;
import org.example.exceptions.UsuarioNotUpdatedException;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();

    Usuario selectById(Long id, Connection conn) throws SQLException;

    Usuario create(Usuario pessoa) throws SQLException, UsuarioNotSavedException;

    Usuario update(Usuario pessoa) throws UsuarioNotFound, UsuarioNotUpdatedException;

    void delete(Long id) throws UsuarioNotFound, SQLException;
}
