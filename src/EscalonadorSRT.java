package src;
import src.Interface_SRT;

import java.util.ArrayList;

public class EscalonadorSRT {

    ArrayList<Processo> listaProcessos = new ArrayList<>();

    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        listaProcessos.add(new Processo(nome, tempoChegada, tempoExecucao));
    }

    /*public void ordenarPorTempoChegada() { //Não utilizada devido ao metódo imprimirResultado percorrer toda a lista sendo ela ordenada ou não

        for (int i = 0; i < listaProcessos.size() - 1; i++) {

            for (int j = i + 1; j < listaProcessos.size(); j++) {

                if (listaProcessos.get(i).tempoChegada > listaProcessos.get(j).tempoChegada) {

                    Processo auxiliar = listaProcessos.get(i);
                    listaProcessos.set(i, listaProcessos.get(j));
                    listaProcessos.set(j, auxiliar);

                }
            }
        }
    }*/

    public void imprimirResultado() {

        int tempoAtual = 0;// esse tempo representa 1 unidade de processamento na CPU

        while (!listaProcessos.isEmpty()) { //Equanto a lista não for vazia vai executar, melhor do que pegar com base na quantidade total de processamento.

            Processo menor = null;
            int index = -1;// -1 pois 0 se refere a uma posição real dentro da lista e -1 não se refere, sendo melhor para não se referir a nenhum ao se inicializar e permitir a alteração ao definir um primeiro processo.

            for (int j = 0; j < listaProcessos.size(); j++) {
                Processo atual = listaProcessos.get(j); // melhora o entendimento já que se refere ao processo atual de indice j no lugar de usar listaProcessos(j).variavel a ser usada

                if (atual.tempoChegada <= tempoAtual) {
                    if (menor == null || atual.tempoExecucao < menor.tempoExecucao) { // condição de ou para nao utilizar 2 ifs
                        menor = atual;
                        index = j;
                    }
                }
            }

            if (menor == null) {// ninguém chegou ainda caso nenhum processo tenha chegado a esse tempo todos acima desse tempo de processamento.
                tempoAtual++; // Aumenta o tempo atual e entra na proxima execução do laço "continue" não executando o que está abaixo
                continue;
            }

            System.out.print(menor.nome + " " + menor.tempoChegada + " " + menor.tempoExecucao + " // ");

            menor.tempoExecucao--; // reduz o tempo de processamento/execução do processo para que ele seja concluido

            if (menor.tempoExecucao <= 0) {
                listaProcessos.remove(index);//Após concluido ele e removido da fila
            }

            tempoAtual++;//aumenta em qual tempo de processamento a cpu esta
        }
    }

}

