package br.repositorio;

import br.model.*;
import br.util.Util;

import java.util.ArrayList;

public class CentralDeInformacoes {

    private ArrayList<Passageiro> todosOsPassageiros;
    private ArrayList<Corrida> todasAsCorridas;

    // Construtor
    public CentralDeInformacoes() {
        this.todosOsPassageiros = new ArrayList<>();
        this.todasAsCorridas = new ArrayList<>();
    }

    public boolean adicionarPassageiro(Passageiro passageiro) {
        // Verifica email duplicado
        for (Passageiro p : todosOsPassageiros) {
            if (p.getEmail().equalsIgnoreCase(passageiro.getEmail())) {
                return false;
            }
        }

        // Verifica idade mínima (18 anos)
        if (Util.calcularIdade(passageiro.getDataNascimento()) < 18) {
            return false;
        }

        todosOsPassageiros.add(passageiro);
        return true;
    }

    public Passageiro recuperarPassageiroPeloEmail(String email) {
        for (Passageiro p : todosOsPassageiros) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Passageiro> getTodosOsPassageiros() {
        return todosOsPassageiros;
    }

    public void setTodosOsPassageiros(ArrayList<Passageiro> todosOsPassageiros) {
        this.todosOsPassageiros = todosOsPassageiros;
    }


    public boolean adicionarCorrida(Corrida corrida) {
        for (Corrida c : todasAsCorridas) {
            if (c.getId() == corrida.getId()) {
                return false;
            }
        }
        todasAsCorridas.add(corrida);
        return true;
    }

    public Corrida recuperarCorridaPeloId(long id) {
        for (Corrida c : todasAsCorridas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public ArrayList<Corrida> recuperarCorridasDeUmPassageiro(String email) {
        Passageiro passageiro = recuperarPassageiroPeloEmail(email);
        if (passageiro == null) {
            return null;
        }

        ArrayList<Corrida> corridasDoPassageiro = new ArrayList<>();
        for (Corrida c : todasAsCorridas) {
            if (c.getPassageiro().getEmail().equalsIgnoreCase(email)) {
                corridasDoPassageiro.add(c);
            }
        }
        return corridasDoPassageiro;
    }

    public ArrayList<Corrida> getTodasAsCorridas() {
        return todasAsCorridas;
    }

    public void setTodasAsCorridas(ArrayList<Corrida> todasAsCorridas) {
        this.todasAsCorridas = todasAsCorridas;
    }
}