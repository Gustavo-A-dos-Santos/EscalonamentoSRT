package src;
import src.Interface_SRT;

import java.util.ArrayList;

public class EscalonadorSRT {

    ArrayList<Processo> listaProcessos = new ArrayList<>();

    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        listaProcessos.add(new Processo(nome, tempoChegada, tempoExecucao));
    }

    public void ordenarPorTempoChegada() {

        for (int i = 0; i < listaProcessos.size() - 1; i++) {

            for (int j = i + 1; j < listaProcessos.size(); j++) {

                if (listaProcessos.get(i).tempoChegada > listaProcessos.get(j).tempoChegada) {

                    Processo auxiliar = listaProcessos.get(i);
                    listaProcessos.set(i, listaProcessos.get(j));
                    listaProcessos.set(j, auxiliar);

                }
            }
        }
    }

    public void imprimirResultado() {

        int tempoAtual = 0;

        while (!listaProcessos.isEmpty()) {

            Processo menor = null;
            int index = -1;

            for (int j = 0; j < listaProcessos.size(); j++) {
                Processo atual = listaProcessos.get(j);

                if (atual.tempoChegada <= tempoAtual) {
                    if (menor == null || atual.tempoExecucao < menor.tempoExecucao) {
                        menor = atual;
                        index = j;
                    }
                }
            }

            if (menor == null) {
                tempoAtual++; // ninguém chegou ainda
                continue;
            }

            System.out.print(menor.nome + " " + menor.tempoChegada + " " + menor.tempoExecucao + " // ");

            menor.tempoExecucao--;

            if (menor.tempoExecucao <= 0) {
                listaProcessos.remove(index);
            }

            tempoAtual++;
        }
    }

}

