package org.example.dao;

import oracle.jdbc.OracleType;
import org.example.config.DatabaseConnectionFactory;
import org.example.exceptions.UsuarioNotFound;
import org.example.exceptions.UsuarioNotSavedException;
import org.example.exceptions.UsuarioTipoInvalido;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UsuarioDaoImp implements UsuarioDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public List<Usuario> findAll(){
        final List<Usuario> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_EC_USUARIOS";
        try(Connection conn = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getString("ds_email"),
                        rs.getString("ds_senha"),
                        rs.getString("tp_usuario"));

                all.add(usuario);
            }
        } catch (SQLException e){
            this.logger.warning("Não foi possível recuperar a lista pessoas");
        } catch (UsuarioTipoInvalido e) {
            throw new RuntimeException(e);
        }
        return all;
    }

    @Override
    public Usuario findById(Long id, Connection conn) throws SQLException {
        final String sql = "SELECT * FROM T_EC_USUARIOS WHERE ID_USUARIO = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1,id);
        ResultSet rs = stmt.executeQuery();
        Usuario usuarioById;
        try {
            rs.next();
            usuarioById = new Usuario ( rs.getLong("id_usuario"),
                                        rs.getString("nm_usuario"),
                                        rs.getString("ds_email"),
                                        rs.getString("ds_senha"),
                                        rs.getString("tp_usuario"));
        } catch (UsuarioTipoInvalido e) {
            throw new RuntimeException(e);
        }
        return usuarioById;
    }

    @Override
    public void save(Usuario usuario, Connection connection) throws SQLException, UsuarioNotSavedException {
        final String sql = "INSERT INTO T_EC_USUARIOS(id_usuario, nm_usuario, ds_email, ds_senha, tp_usuario) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, usuario.getId());
        stmt.setString(2, usuario.getNome());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getSenha());
        stmt.setString(5, usuario.getTipo());

        int linhasAlteradas = stmt.executeUpdate();

        if(linhasAlteradas == 0) {
            throw new UsuarioNotSavedException();
        }
    }

    @Override
    public Usuario update(Usuario usuario, Connection connection) throws SQLException, UsuarioNotFound {
        final String sql = "UPDATE T_EC_USUARIOS set NM_USUARIO=?, ds_email=?, ds_senha=?, tp_usuario=? where ID_USUARIO = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1,usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getTipo());
        stmt.setLong(5,usuario.getId());
        int linhasAlteradas = stmt.executeUpdate();
        if (linhasAlteradas != 1) {
            throw new UsuarioNotFound(usuario.getId());
        }
        return usuario;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, UsuarioNotFound {
        final String sql = "DELETE T_EC_USUARIOS WHERE ID_USUARIO=?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1,id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new UsuarioNotFound(id);
        }
    }
}
