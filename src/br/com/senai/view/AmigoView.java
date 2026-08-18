package br.com.senai.view;

import br.com.senai.controller.AmigoController;
import br.com.senai.driver.MySQLConnect;
import br.com.senai.model.Amigo;
import br.com.senai.model.AmigoDAO;
import br.com.senai.model.Genero;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
            System.out.println("4 - Desativar amigo");
            System.out.println("5 - Detalhar amigo");

            System.out.println("Digite sua opção: ");
            int opcao = Integer.parseInt(sc.nextLine());

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
                    String email = sc.nextLine();

                    System.out.println("Informe a data de nascimento do amigo: (yyyy-mm-dd)");
                    Date data_nascimento = Date.valueOf(sc.nextLine());

                    System.out.println("Informe o genero do amigo: (MASCULINO/FEMININO)");
                    Genero genero = Genero.valueOf(sc.nextLine().toUpperCase());

                    Amigo amigo = new Amigo(null, nome, telefone, email, data_nascimento, genero);

                    System.out.println(amigo);

                    boolean cadastradoComSucesso = amigoController.cadastrarAmigo(amigo);
                    if (cadastradoComSucesso) {
                        System.out.println("Amigo cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar amigo!");
                    }
                    System.out.println("------------------------------" + "\n");
                }

                case 2 -> {
                    List<Amigo> amigos = amigoController.listarAmigos();

                    if(amigos.isEmpty()){
                        System.out.println("Nenhum amigo cadastrado!\n");
                    } else {
                        System.out.println("---- Lista de amigos ----");
                        for (Amigo amigoTemp : amigos) {
                            System.out.println(amigoTemp.readList());
                        }
                    }
                    System.out.println("------------------------------" + "\n");
                }

                case 3 -> {
                    System.out.println("Informe o id do amigo que deseja atualizar: ");
                    Integer id = Integer.parseInt(sc.nextLine());

                    System.out.println("Atualize o nome (ou aperte Enter para manter o atual): ");
                    String nome = sc.nextLine();
                    if (nome.trim().isEmpty()) nome = null;

                    System.out.println("Atualize o telefone (ou aperte Enter para manter o atual):");
                    String telefone = sc.nextLine();
                    if (telefone.trim().isEmpty()) telefone = null;

                    System.out.println("Atualize o email (ou aperte Enter para manter o atual): ");
                    String email = sc.nextLine();
                    if (email.trim().isEmpty()) email = null;

                    System.out.println("Atualize a data de nascimento (yyyy-mm-dd) (ou aperte Enter para manter):");
                    String dataStr = sc.nextLine();
                    Date data_nascimento = dataStr.trim().isEmpty() ? null : Date.valueOf(dataStr);

                    System.out.println("Atualize o genero (MASCULINO/FEMININO) (ou aperte Enter para manter):");
                    String generoStr = sc.nextLine();
                    Genero genero = generoStr.trim().isEmpty() ? null : Genero.valueOf(generoStr.toUpperCase());

                    Amigo amigo = new Amigo(id, nome, telefone, email, data_nascimento, genero);

                    boolean atualizadoComSucesso = amigoController.atualizarAmigo(amigo);

                    if (atualizadoComSucesso) {
                        System.out.println("Amigo atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar amigo!");
                    }
                }

                case 4 -> {
                    System.out.println("Informe o id do amigo que deseja desativar: ");
                    Integer id = Integer.parseInt(sc.nextLine());

                    boolean deletadoComSucesso = amigoController.desativarAmigo(id);

                    if (deletadoComSucesso) {
                        System.out.println("Amigo desativado com sucesso!");
                    } else {
                        System.out.println("Erro ao desativar amigo!");
                    }
                }

                case 5 -> {
                    System.out.println("Informe o id do amigo que deseja detalhar: ");
                    Integer id = Integer.parseInt(sc.nextLine());

                    Amigo amigo = amigoController.detalharAmigo(id);

                    if (amigo != null) {
                        System.out.println("Dados do amigo detalhados com sucesso!");
                        System.out.println(amigo);
                    } else  {
                        System.out.println("Erro ao detalhar os dados do amigo!");
                    }
                }

                default -> {
                    System.out.println("Numero invalido! Escolha um numero de 0 a 5!" + "\n" );
                }

            }
        }

    }
}
