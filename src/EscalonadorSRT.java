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


}

