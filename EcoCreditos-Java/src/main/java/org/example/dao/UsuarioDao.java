package org.example.dao;

import org.example.exceptions.UsuarioNotFound;
import org.example.exceptions.UsuarioNotSavedException;
import org.example.exceptions.UsuarioTipoInvalido;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {


    List<Usuario> findAll();

    Usuario findById(Long id, Connection connection) throws SQLException;

    void save(Usuario usuario, Connection connection) throws SQLException, UsuarioNotSavedException;

    Usuario update(Usuario usuario, Connection connection) throws SQLException, UsuarioNotFound;

    void deleteById(Long id, Connection connection) throws SQLException ,UsuarioNotFound;

}
