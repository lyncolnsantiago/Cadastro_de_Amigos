package br.com.senai.controller;

import br.com.senai.model.Amigo;
import br.com.senai.model.AmigoDAO;

import java.sql.SQLException;
import java.util.List;

public class AmigoController {
    private final AmigoDAO AMIGO_DAO;

    public AmigoController(AmigoDAO AMIGO_DAO) {
        this.AMIGO_DAO = AMIGO_DAO;
    }

    public boolean cadastrarAmigo(Amigo amigo) throws SQLException {
        return AMIGO_DAO.create(amigo);
    }

    public List<Amigo> listarAmigos() throws SQLException {
        return AMIGO_DAO.read();
    }

    public Amigo detalharAmigo(int id) throws SQLException {
        return AMIGO_DAO.detail(id);
    }

    public boolean atualizarAmigo(Amigo amigo) throws SQLException {
        return AMIGO_DAO.update(amigo);
    }

    public boolean desativarAmigo(int id) throws SQLException {
        return AMIGO_DAO.soft_delete(id);
    }
}
