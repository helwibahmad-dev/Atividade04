package br.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Passageiro {
    private String nome;
    private Sexo sexo;
    private Date dataNascimento;
    private String email;

    public Passageiro(String nome, Sexo sexo, Date dataNascimento, String email) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome;
    }
}