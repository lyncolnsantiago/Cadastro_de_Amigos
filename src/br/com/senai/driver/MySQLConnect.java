package br.com.senai.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
    private Connection con;

    private final String DB_PATH = "jdbc:mysql://localhost:3306/cadastro_amigos";
    private final String USER = "root";
    private final String PASSWORD = "senai";

    public boolean openConnect() {
        try {
            con = DriverManager.getConnection(DB_PATH, USER, PASSWORD);
            System.out.println("Conectado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println("Problema na conexão com a base de dados: " + e.getMessage());
            return false;
        }
    }

    public void closeConnect() {
        try {
            con.close();
            System.out.println("Desconectado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Problema ao desconectar usuário: " +
                    e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return con;
    }
}
