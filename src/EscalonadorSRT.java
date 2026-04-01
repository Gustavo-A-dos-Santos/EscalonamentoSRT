package src;
import src.Interface_SRT;

import java.util.ArrayList;

public class EscalonadorSRT implements Interface_SRT {
    /*Entrada Usuario*/
    ArrayList<String> nomesProcessos = new ArrayList<String>();
    ArrayList<Integer> temposExecucao = new ArrayList<Integer>();
    ArrayList<Integer> temposChegada = new ArrayList<Integer>();
    private int quantidadeProcessos;
    private int tempoAtual;
    private int totalExecucao = 0;

    public void tempoTotalExecucao(){
        for (int i = 0; i < quantidadeProcessos; i++) {
            totalExecucao+=temposExecucao.get(i);
        }

    }
   @Override
    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        nomesProcessos.add(nome);
        temposChegada.add(tempoChegada);
        temposExecucao.add(tempoExecucao);
        quantidadeProcessos++;
    }
    public void mostrarProcessos() {
        tempoTotalExecucao();
        MyArrayList arrayOrdenado = new MyArrayList(quantidadeProcessos);
        for (tempoAtual=0; tempoAtual <= totalExecucao; tempoAtual++) {
            for (int i = 0; i < quantidadeProcessos; i++) {
                if (temposChegada.get(i) >= tempoAtual) {
                    arrayOrdenado.add(i);
                    System.out.println(nomesProcessos.get(i));
                }
            }
        }
    }


}

