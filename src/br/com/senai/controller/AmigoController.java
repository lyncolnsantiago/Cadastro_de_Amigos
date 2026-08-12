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

    public void cadastrarAmigo(Amigo amigo) throws SQLException {
        if (AMIGO_DAO.create(amigo)) {
            System.out.println("Controller: Cadastro realizado com sucesso! \n");
        } else {
            System.out.println("Controller: Erro ao cadastrar amigo! \n");
        }
    }

    public void listarAmigos() throws SQLException {
        List<Amigo> amigos = AMIGO_DAO.read();
        if(amigos.isEmpty()){
            System.out.println("Controller: Nenhum registro encontrado! \n");
        } else {
            System.out.println("Lista de amigos:");
            for (Amigo amigoTemp : amigos) {
                System.out.println(amigoTemp);
            }
        }
    }

    public void atualizarAmigo(Amigo amigo) throws SQLException {
        if (AMIGO_DAO.update(amigo)) {
            System.out.println("Controller: Atualização realizada com sucesso! \n");
        } else {
            System.out.println("Controller: Erro ao atualizar amigo! \n");
        }
    }

    public void excluirAmigo(int id) throws SQLException {
        if (AMIGO_DAO.delete(id)) {
            System.out.println("Controller: Exclusão realizada com sucesso! \n");
        } else {
            System.out.println("Controller: Erro ao excluir amigo! \n");
        }
    }

    public void desativarAmigo(int id) throws SQLException {
        if (AMIGO_DAO.soft_delete(id)) {
            System.out.println("Controller: Desativação realizada com sucesso! \n");
        } else  {
            System.out.println("Controller: Erro ao desativar amigo! \n");
        }
    }
}
