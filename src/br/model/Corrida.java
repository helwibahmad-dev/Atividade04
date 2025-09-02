package br.model;

public class Corrida {
    private long id;
    private String enderecoPartida;
    private String enderecoDestino;
    private Passageiro passageiro;

    public Corrida() {
        this.id = System.currentTimeMillis();
    }

    public Corrida(String enderecoPartida, String enderecoDestino, Passageiro passageiro) {
        this.id = System.currentTimeMillis();
        this.enderecoPartida = enderecoPartida;
        this.enderecoDestino = enderecoDestino;
        this.passageiro = passageiro;
    }

    public long getId() {
        return id;
    }

    public String getEnderecoPartida() {
        return enderecoPartida;
    }

    public void setEnderecoPartida(String enderecoPartida) {
        this.enderecoPartida = enderecoPartida;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }
    @Override
    public String toString() {
        String pronome = (passageiro.getSexo() == Sexo.Feminino) ? "a" : "o";
        return passageiro.getNome() + " pede para pegá-" + pronome + " em " + enderecoPartida;
    }
}