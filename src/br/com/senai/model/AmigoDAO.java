package br.com.senai.model;

import br.com.senai.driver.MySQLConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmigoDAO {
    private final MySQLConnect DB_CONNECT;

    public AmigoDAO(MySQLConnect DB_CONNECT) {
        this.DB_CONNECT = DB_CONNECT;
    }

    public boolean create(Amigo amigo) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        String query = "INSERT INTO amigos(nome, telefone, email, data_nascimento, genero) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, amigo.getNome());
            ps.setString(2, amigo.getTelefone());
            ps.setString(3, amigo.getEmail());
            ps.setDate(4, amigo.getData_nascimento());
            ps.setString(5, amigo.getGenero().name());

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }
    }

    public List<Amigo> read() throws SQLException {
        List<Amigo> amigos = new ArrayList<>();
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return amigos;
        }

        String query = "SELECT * FROM amigos WHERE ativo = true;";
        try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Amigo amigo = new Amigo(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento"),
                        Genero.valueOf(rs.getString("genero"))
                );
                amigos.add(amigo);
            }
            return amigos;
        }
    }

    public boolean update(Amigo amigo) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        String query = "UPDATE amigos SET nome = ?, telefone = ?, email = ?, data_nascimento = ?, genero = ? WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, amigo.getNome());
            ps.setString(2, amigo.getTelefone());
            ps.setString(3, amigo.getEmail());
            ps.setDate(4, amigo.getData_nascimento());
            ps.setString(5, amigo.getGenero().name());

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        String query = "DELETE FROM amigos WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }
    }

    public boolean soft_delete(int id) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        String query = "UPDATE amigos SET ativo = false AND id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }
    }
}
