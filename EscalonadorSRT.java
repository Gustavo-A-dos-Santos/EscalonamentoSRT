public class EscalonadorSRT {
    /*Entrada Usuario*/
    private String[] nomesProcessos;
    private int[] temposExecucao;
    private int[] temposChegada;
    private int quantidadeProcessos;
    private int tempoAtual;
    private int capacidadeMaxima;

    public EscalonadorSRT(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
        nomesProcessos   = new String[capacidadeMaxima];
        temposExecucao   = new int[capacidadeMaxima];
        temposChegada    = new int[capacidadeMaxima];
        quantidadeProcessos = 0;
    }

    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        nomesProcessos[quantidadeProcessos] = nome;
        temposChegada[quantidadeProcessos] = tempoChegada;
        temposExecucao[quantidadeProcessos] = tempoExecucao;
        quantidadeProcessos++;
    }

    public void ordenarPorTempoRestante() {
        int[] arraytemp = new int[quantidadeProcessos];
        for (int i = 1; i < quantidadeProcessos; i++) {
            if (temposExecucao[i] <= tempoAtual) {
                // lógica aqui
            }
        }
    }
}

