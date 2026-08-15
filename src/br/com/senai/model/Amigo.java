package br.com.senai.model;

import java.sql.Date;

public class Amigo {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private Date data_nascimento;
    private Genero genero;
    private boolean ativo;

    public Amigo() {
    }

    public Amigo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Amigo(Integer id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Amigo(Integer id, String nome, String telefone, String email, Date data_nascimento, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
    }

    public Amigo(Integer id, String nome, String telefone, String email, Date data_nascimento, Genero genero, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.data_nascimento = data_nascimento;
        this.genero = genero;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public Genero getGenero() {
        return genero;
    }

    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public String toString() {
        return "Amigo: " + id +
                "\nNome: " + nome +
                "\nTelefone: " + telefone +
                "\nEmail: " + email +
                "\nAniversario: " + data_nascimento +
                "\nGenero: " + genero.name() +
                "\nAtivo: " + ativo + "\n";
    }

    public String readList() {
        return "Amigo: " + this.id +
                "\nNome: " + this.nome +
                "\nTelefone: " + this.telefone + "\n";
    }
}
