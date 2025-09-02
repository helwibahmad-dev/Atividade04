package br.principal;

import br.model.Corrida;
import br.model.Passageiro;
import br.model.Sexo;
import br.persistencia.Persistencia;
import br.repositorio.CentralDeInformacoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
    private static final String ARQUIVO_XML = "central.xml";
    private static Scanner scanner = new Scanner(System.in);
    private static Persistencia persistencia = new Persistencia();
    private static CentralDeInformacoes central;

    public static void main(String[] args) {
        // Tenta recuperar central do XML
        central = persistencia.recuperarCentral(ARQUIVO_XML);

        String opcao;
        do {
            exibirMenu();
            opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "1":
                    cadastrarPassageiro();
                    break;
                case "2":
                    listarTodosPassageiros();
                    break;
                case "3":
                    exibirPassageiroEspecifico();
                    break;
                case "4":
                    cadastrarCorrida();
                    break;
                case "5":
                    listarTodasCorridas();
                    break;
                case "6":
                    listarCorridasDePassageiro();
                    break;
                case "S":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (!opcao.equals("S"));
    }

    private static void exibirMenu() {
        System.out.println("\n----------MENU-PASSAGEIRO-----------");
        System.out.println("1 - Novo Passageiro                  |");
        System.out.println("2 - Listar todos os Passageiros      |");
        System.out.println("3 - Exibir Passageiro específico     |");
        System.out.println("4 - Nova Corrida                     |");
        System.out.println("5 - Listar todas as Corridas         |");
        System.out.println("6 - Listar Corridas de um Passageiro |");
        System.out.println("S - Sair                             |");
        System.out.println("--------------------------------------");
        System.out.print("Escolha uma opção:");

    }

    private static void cadastrarPassageiro() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Sexo (M/F): ");
            Sexo sexo = scanner.nextLine().trim().equalsIgnoreCase("F") ? Sexo.Feminino : Sexo.Masculino;

            System.out.print("Data de nascimento (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataStr);

            System.out.print("Email: ");
            String email = scanner.nextLine();

            Passageiro passageiro = new Passageiro(nome, sexo, dataNascimento, email);
            boolean sucesso = central.adicionarPassageiro(passageiro);

            if (sucesso) {
                persistencia.salvarCentral(central, ARQUIVO_XML);
                System.out.println("Passageiro cadastrado com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar o passageiro (email duplicado ou menor de 18 anos).");
            }

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar passageiro: " + e.getMessage());
        }
    }

    private static void listarTodosPassageiros() {
        ArrayList<Passageiro> passageiros = central.getTodosOsPassageiros();
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
        } else {
            System.out.println("=== Passageiros Cadastrados ===");
            for (Passageiro p : passageiros) {
                System.out.println("- " + p.getNome() + " (" + p.getEmail() + ")");
            }
        }
    }

    private static void exibirPassageiroEspecifico() {
        System.out.print("Digite o email do passageiro: ");
        String email = scanner.nextLine();
        Passageiro p = central.recuperarPassageiroPeloEmail(email);
        if (p == null) {
            System.out.println("Passageiro não encontrado.");
        } else {
            System.out.println("Nome: " + p.getNome());
            System.out.println("Sexo: " + p.getSexo());
            System.out.println("Data de nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(p.getDataNascimento()));
            System.out.println("Email: " + p.getEmail());
        }
    }
    private static void cadastrarCorrida() {
        System.out.print("Digite o email do passageiro: ");
        String email = scanner.nextLine();
        Passageiro passageiro = central.recuperarPassageiroPeloEmail(email);

        if (passageiro == null) {
            System.out.println("Passageiro não encontrado.");
            return;
        }

        System.out.print("Endereço de partida: ");
        String partida = scanner.nextLine();

        System.out.print("Endereço de destino: ");
        String destino = scanner.nextLine();

        Corrida corrida = new Corrida(partida, destino, passageiro);
        boolean sucesso = central.adicionarCorrida(corrida);

        if (sucesso) {
            persistencia.salvarCentral(central, ARQUIVO_XML);
            System.out.println("Corrida cadastrada com sucesso! ID: " + corrida.getId());
        } else {
            System.out.println("Não foi possível cadastrar a corrida (ID duplicado).");
        }
    }

    private static void listarTodasCorridas() {
        ArrayList<Corrida> corridas = central.getTodasAsCorridas();
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida cadastrada.");
        } else {
            System.out.println("=== Todas as Corridas ===");
            for (Corrida c : corridas) {
                System.out.println("- " + c.toString() + " | ID: " + c.getId());
            }
        }
    }

    private static void listarCorridasDePassageiro() {
        System.out.print("Digite o email do passageiro: ");
        String email = scanner.nextLine();

        ArrayList<Corrida> corridas = central.recuperarCorridasDeUmPassageiro(email);

        if (corridas == null) {
            System.out.println("Passageiro não existe.");
        } else if (corridas.isEmpty()) {
            System.out.println("O passageiro não possui corridas cadastradas.");
        } else {
            System.out.println("=== Corridas de " + email + " ===");
            for (Corrida c : corridas) {
                System.out.println("- " + c.toString() + " | ID: " + c.getId());
            }
        }
    }
}
