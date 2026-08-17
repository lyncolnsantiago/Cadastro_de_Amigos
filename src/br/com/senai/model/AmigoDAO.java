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
        String query = "INSERT INTO amigos(nome, telefone, email, data_nascimento, genero, ativo) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, amigo.getNome());
            ps.setString(2, amigo.getTelefone());
            ps.setString(3, amigo.getEmail());
            ps.setDate(4, amigo.getData_nascimento());
            ps.setString(5, amigo.getGenero().name());
            ps.setBoolean(6, true); //Aqui eu forcei o registro ser criado como ativo no banco!

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
                        rs.getString("telefone")
                );
                amigos.add(amigo);
            }
            return amigos;
        }
    }

    public Amigo detail(Integer id)  throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return null;
        }

        String query = "SELECT * FROM amigos WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Amigo(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("telefone"),
                            rs.getString("email"),
                            rs.getDate("data_nascimento"),
                            Genero.valueOf(rs.getString("genero")),
                            rs.getBoolean("ativo")
                    );
                }
            }
        }
        return null;
    }

    public boolean update(Amigo amigo) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        StringBuilder query = new StringBuilder("UPDATE amigos SET ");
        List<Object> parametros = new ArrayList<>();

        if (amigo.getNome() != null && !amigo.getNome().trim().isEmpty()) {
            query.append("nome = ?, ");
            parametros.add(amigo.getNome());
        }
        if (amigo.getTelefone() != null && !amigo.getTelefone().trim().isEmpty()) {
            query.append("telefone = ?, ");
            parametros.add(amigo.getTelefone());
        }
        if (amigo.getEmail() != null && !amigo.getEmail().trim().isEmpty()) {
            query.append("email = ?, ");
            parametros.add(amigo.getEmail());
        }
        if (amigo.getData_nascimento() != null) {
            query.append("data_nascimento = ?, ");
            parametros.add(amigo.getData_nascimento());
        }
        if (amigo.getGenero() != null) {
            query.append("genero = ?, ");
            parametros.add(amigo.getGenero());
        }

        if (parametros.isEmpty()) {
            System.out.println("Nenhum dado informado para atualização");
            return false;
        }

        query.setLength(query.length() - 2);

        query.append(" WHERE id = ?");
        parametros.add(amigo.getId());

        try (PreparedStatement ps = con.prepareStatement(query.toString())) {
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }

    }

    public boolean soft_delete(int id) throws SQLException {
        Connection con = DB_CONNECT.getConnection();
        if (con == null) {
            return false;
        }
        String query = "UPDATE amigos SET ativo = false WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);

            int linhasModificadas = ps.executeUpdate();
            return linhasModificadas > 0;
        }
    }
}
