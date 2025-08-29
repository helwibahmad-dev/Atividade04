package exercicio01;

import java.util.Date;

public class Passageiro {
    private String nome;
    private char sexo;
    private Date dtNascimento;
    private String email;

    public Passageiro(String nome, char sexo, Date dtNascimento, String email) {
        this.nome = nome;
        this.sexo = sexo;
        this.dtNascimento = dtNascimento;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail() {
        return email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Passageiro{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
