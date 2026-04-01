package src;
import src.Interface_SRT;

public class EscalonadorSRT implements Interface_SRT {
    /*Entrada Usuario*/
    private String[] nomesProcessos;
    private int[] temposExecucao;
    private int[] temposChegada;
    private int quantidadeProcessos;
    private int tempoAtual;
    private int totalExecucao = 0;

    public EscalonadorSRT(int capacidadeMaxima) {
        nomesProcessos   = new String[capacidadeMaxima];
        temposExecucao   = new int[capacidadeMaxima];
        temposChegada    = new int[capacidadeMaxima];
        quantidadeProcessos = 0;
    }

    public void tempoTotalExecucao(){
        for (int i = 0; i < quantidadeProcessos; i++) {
            totalExecucao+=temposExecucao[i];
        }

    }
   @Override
    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        nomesProcessos[quantidadeProcessos] = nome;
        temposChegada[quantidadeProcessos] = tempoChegada;
        temposExecucao[quantidadeProcessos] = tempoExecucao;
        quantidadeProcessos++;
    }
    public void mostrarProcessos() {
        for (tempoAtual=0; tempoAtual <= totalExecucao; tempoAtual++) {
            for (int i = 0; i < quantidadeProcessos; i++) {
                if (temposChegada[i] >= tempoAtual) {
                    System.out.println(nomesProcessos[i]);
                }
            }
        }
    }


}

