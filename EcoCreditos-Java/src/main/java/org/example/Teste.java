package org.example;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.UsuarioDao;
import org.example.dao.UsuarioDaoFactory;
import org.example.dao.UsuarioDaoImp;
import org.example.exceptions.UsuarioNotFound;
import org.example.exceptions.UsuarioNotSavedException;
import org.example.exceptions.UsuarioTipoInvalido;
import org.example.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {
    private static UsuarioDao dao = UsuarioDaoFactory.create();

    public static void main(String[] args) throws UsuarioTipoInvalido, SQLException, UsuarioNotSavedException {

        var usuario = new Usuario(3L, "Ana Maria", "am.rainha@mail.com", "12345", "admin");
        var novoUsuario = new Usuario(3L, "Rick", "cardo@mail.com", "java", "admin");

        //System.out.println(usuario);

        Connection conn = DatabaseConnectionFactory.create().get();



        //Insert
//        try {
//            dao.save(usuario,conn);
//            conn.commit();
//        } catch (SQLException | UsuarioNotSavedException e) {
//            conn.rollback();
//            throw e;
//        }


        //Update
//        try {
//            usuario = dao.update(novoUsuario,conn);
//            conn.commit();
//        } catch (UsuarioNotFound e) {
//            throw new RuntimeException(e);
//        }


        //Delete
        try {
            dao.deleteById(usuario.getId(),conn);
        } catch (UsuarioNotFound e) {
            throw new RuntimeException(e);
        }
        conn.commit();


        //Id
        System.out.println(dao.findById(1L, conn));

        //All
        System.out.println(dao.findAll());

    }
}
