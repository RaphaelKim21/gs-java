package org.example.service;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.UsuarioDao;
import org.example.dao.UsuarioDaoFactory;
import org.example.exceptions.UsuarioInvalid;
import org.example.exceptions.UsuarioNotFound;
import org.example.exceptions.UsuarioNotSavedException;
import org.example.exceptions.UsuarioNotUpdatedException;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UsuarioServiceImp implements UsuarioService{

    private UsuarioDao dao = UsuarioDaoFactory.create();

    @Override
    public List<Usuario> listarTodos() {
        return this.dao.findAll();
    }

    @Override
    public Usuario selectById(Long id, Connection conn) throws SQLException {
        return this.dao.findById(id, conn);
    }

    @Override
    public Usuario create(Usuario usuario) throws  SQLException, UsuarioNotSavedException {

        Connection conn = DatabaseConnectionFactory.create().get();
        try {
            this.dao.save(usuario,conn);
            conn.commit();
        } catch (SQLException | UsuarioNotSavedException  e) {
            conn.rollback();
            throw e;
        }
        return usuario;
    }

    @Override
    public Usuario update(Usuario pessoa) throws UsuarioNotFound, UsuarioNotUpdatedException {
        try (Connection conn = DatabaseConnectionFactory.create().get()){
            pessoa = this.dao.update(pessoa,conn);
            conn.commit();
        } catch (SQLException e) {
            throw new UsuarioNotUpdatedException();
        }
        return pessoa;
    }

    @Override
    public void delete(Long id) throws UsuarioNotFound, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id,connection);
        connection.commit();
    }
}
