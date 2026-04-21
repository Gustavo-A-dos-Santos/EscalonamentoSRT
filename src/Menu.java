package src;

import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    EscalonadorSRT ec = new EscalonadorSRT();
    String opcao;
    int tempoC, tempoEx;

    public void iniciar() {

        System.out.println("******** GERENCIADOR DE PROCESSOS ********\n");

        do {
            System.out.print("Digite o nome do Processo: ");
            String nome = sc.nextLine();

            VerificarTempoDeChegada();
            VerificarTempoDeExecucacao();

            ec.adicionarProcesso(nome, tempoC, tempoEx);
            System.out.println("Processo '" + nome + "' adicionado com sucesso!\n");
            System.out.print("Deseja adicionar outro Processo? [S/N]: ");
            opcao = sc.nextLine();
            System.out.println();

        } while (opcao.equalsIgnoreCase("s"));

        System.out.println("Todos os processos foram adicionados!");
        System.out.println("Iniciando escalonamento SRT");
        System.out.println("**Imprimindo Tabela de Processos Finais**");
        ec.imprimirResultado();
        sc.close();
    }

    public void VerificarTempoDeChegada() {

        while (true) {
            System.out.print("Digite o Tempo de Chegada do Processo: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número inteiro:");
                sc.next();
            }

            tempoC = sc.nextInt();
            sc.nextLine();
            if (tempoC >= 0) {
                break;
            } else {
                System.out.println("O tempo não pode ser negativo!");
            }
        }

    }

    public void VerificarTempoDeExecucacao() {

        while (true) {
            System.out.print("Digite o Tempo de Execução do Processo: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida! Digite um número inteiro:");
                sc.next();
            }

            tempoEx = sc.nextInt();
            sc.nextLine();
            if (tempoEx > 0) {
                break;
            } else {
                System.out.println("O tempo de execução deve ser maior que zero!");
            }
        }
    }
}