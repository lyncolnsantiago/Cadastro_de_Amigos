package br.com.senai.view;

import br.com.senai.controller.AmigoController;
import br.com.senai.driver.MySQLConnect;
import br.com.senai.model.Amigo;
import br.com.senai.model.AmigoDAO;
import br.com.senai.model.Genero;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class AmigoView {
    public static void mostrarMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        MySQLConnect dbConnect = new MySQLConnect();
        if (!dbConnect.openConnect()) {
            System.out.println("O sistema não está conectado com o banco de dados!");
            return;
        }
        AmigoDAO amigoDAO = new AmigoDAO(dbConnect);
        AmigoController amigoController = new AmigoController(amigoDAO);

        while (true) {
            System.out.println("Cadastro de amigos");
            System.out.println("Menu: ");
            System.out.println("0 - Sair do programa");
            System.out.println("1 - Cadastrar amigo");
            System.out.println("2 - Listar amigos");
            System.out.println("3 - Atualizar dados");
            System.out.println("4 - Excluir amigo");
            System.out.println("5 - Desativar amigo");

            System.out.println("Digite sua opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); //limpa o buffer

            switch (opcao) {
                case 0 -> {
                    dbConnect.closeConnect();
                    System.out.println("Programa finalizado!");
                    System.exit(0);
                    sc.close();
                }
                case 1 -> {
                    System.out.println("Informe o nome do amigo: ");
                    String nome = sc.nextLine();

                    System.out.println("Informe o telefone do amigo:");
                    String telefone = sc.nextLine();

                    System.out.println("Informe o email do amigo: ");
                    String email = sc.next();

                    System.out.println("Informe a data de nascimento do amigo: (yyyy-mm-dd)");
                    Date data_nascimento = Date.valueOf(sc.next());
                    sc.nextLine();

                    System.out.println("Informe o genero do amigo: (MASCULINO/FEMININO)");
                    Genero genero = Genero.valueOf(sc.next().toUpperCase());
                    sc.nextLine();

                    Amigo amigo = new Amigo(null, nome, telefone, email, data_nascimento, genero);

                    System.out.println(amigo);

                    amigoController.cadastrarAmigo(amigo);
                }

                case 2 -> amigoController.listarAmigos();

                case 3 -> {
                    System.out.println("Informe o id do amigo que deseja atualizar: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Atualize o nome do amigo: ");
                    String nome = sc.nextLine();

                    System.out.println("Atualize o telefone do amigo:");
                    String telefone = sc.nextLine();

                    System.out.println("Atualize o email do amigo: ");
                    String email = sc.next();

                    System.out.println("Atualize a data de nascimento do amigo: (yyyy-mm-dd)");
                    Date data_nascimento = Date.valueOf(sc.next());
                    sc.nextLine();

                    System.out.println("Atualize o genero do amigo: (MASCULINO/FEMININO)");
                    Genero genero = Genero.valueOf(sc.next().toUpperCase());
                    sc.nextLine();

                    Amigo amigo = new Amigo(id, nome, telefone, email, data_nascimento, genero);
                    amigoController.atualizarAmigo(amigo);
                }

                case 4 -> {
                    System.out.println("Informe o id do amigo que deseja excluir: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();

                    amigoController.excluirAmigo(id);
                }

                case 5 -> {
                    System.out.println("Informe o id do amigo que deseja desativar: ");
                    Integer id = sc.nextInt();
                    sc.nextLine();

                    amigoController.desativarAmigo(id);
                }
            }
        }

    }
}
