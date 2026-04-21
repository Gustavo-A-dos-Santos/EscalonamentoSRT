package src;

import java.util.ArrayList;
import java.util.Random;

public class EscalonadorSRT {

    // Lista que armazena todos os processos que ainda precisam ser executados
    ArrayList<Processo> listaProcessos = new ArrayList<>();

    // Método para adicionar um processo na lista
    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        listaProcessos.add(new Processo(nome, tempoChegada, tempoExecucao));
    }

    // Método principal: simula o escalonamento SRT (Shortest Remaining Time)
    public void imprimirResultado() {

        int tempoAtual = 0; // controla o tempo da CPU
        Processo anterior = null; // guarda o processo que estava executando antes
        int indexAt = -1; // guarda o índice do processo anterior

        // Essa lista vai armazenar QUAL processo foi executado em cada unidade de tempo
        ArrayList<String> imprimirProcessos = new ArrayList<>();

        // Enquanto ainda existirem processos para executar
        while (!listaProcessos.isEmpty()) {

            // Escolhe o melhor processo com base no tempo atual
            int indexEscolhido = escolherProcesso(tempoAtual);

            // Se nenhum processo chegou ainda, CPU fica ociosa
            if (indexEscolhido == -1) {
                imprimirProcessos.add("*");
                tempoAtual++;
                continue;
            }

            Processo menor;
            int index;

            // Pegamos o tempo de execução do processo escolhido
            int menorExecucao = listaProcessos.get(indexEscolhido).tempoExecucao;

            /* Se dois processos têm o mesmo tempo restante,
              continuamos com o que já estava executando (evita troca desnecessária)
             */
            if (anterior != null && anterior.tempoExecucao == menorExecucao) {
                menor = anterior;
                index = indexAt;
            } else {
                menor = listaProcessos.get(indexEscolhido);
                index = indexEscolhido;
            }

            // Guarda qual processo executou nesse tempo (para montar o gráfico depois)
            imprimirProcessos.add(menor.nome);

            // Executa o processo por 1 unidade de tempo
            menor.tempoExecucao--;

            // Se terminou a execução, remove da lista
            if (menor.tempoExecucao <= 0) {
                listaProcessos.remove(index);
                anterior = null;
            } else {
                // Senão, ele continua como o processo anterior
                anterior = menor;
                indexAt = index;
            }

            // Avança o tempo da CPU
            tempoAtual++;
        }

        // Depois de simular tudo, imprime o gráfico
        imprimir(imprimirProcessos);
    }

    // Método responsável por escolher o próximo processo (regra do SRT)
    public int escolherProcesso(int tempoAtual) {

        int menorExecucao = Integer.MAX_VALUE;

        /*
         * Primeiro: descobrimos qual é o menor tempo restante
         * entre os processos que JÁ CHEGARAM
         */
        for (Processo atual : listaProcessos) {
            if (atual.tempoChegada <= tempoAtual) {
                if (atual.tempoExecucao < menorExecucao) {
                    menorExecucao = atual.tempoExecucao;
                }
            }
        }

        // Lista de processos que possuem o menor tempo restante
        ArrayList<Integer> possiveis = new ArrayList<>();

        for (int i = 0; i < listaProcessos.size(); i++) {
            Processo p = listaProcessos.get(i);

            if (p.tempoChegada <= tempoAtual && p.tempoExecucao == menorExecucao) {
                possiveis.add(i);
            }
        }

        // Se não há processos disponíveis ainda
        if (possiveis.isEmpty()) {
            return -1;
        }

        /*
         * Se houver empate (mesmo tempo restante),
         * escolhemos aleatoriamente (simula decisão da CPU)
         */
        Random rand = new Random();
        return possiveis.get(rand.nextInt(possiveis.size()));
    }

    // Método responsável por imprimir o gráfico estilo Tabela
    public void imprimir(ArrayList<String> imprimirProcessos) {

        System.out.println("TEMPO");

        // Linha de tempo (0, 1, 2, 3...)
        for (int i = 0; i < imprimirProcessos.size(); i++) {
            System.out.print(i + " | ");
        }

        System.out.println("\n--------------------------------------------------");

        // Linha com os processos executados em cada tempo
        for (String p : imprimirProcessos) {
            System.out.print(p + " | ");
        }

        System.out.print("\nPROCESSOS ");
    }
}