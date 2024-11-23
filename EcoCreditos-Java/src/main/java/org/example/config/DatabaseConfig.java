package org.example.config;

final class DatabaseConfig {

    private DatabaseConfig() {
        throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    static String getUser(){
        return "rm558471";
    }

    static String getPassword(){
        return "080203";
    }

}