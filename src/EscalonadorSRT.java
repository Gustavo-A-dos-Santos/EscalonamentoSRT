package src;

import java.util.ArrayList;
import java.util.Random;

public class EscalonadorSRT {

    ArrayList<Processo> listaProcessos = new ArrayList<>();

    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        listaProcessos.add(new Processo(nome, tempoChegada, tempoExecucao));
    }

    /*public void ordenarPorTempoChegada() { //Não utilizada devido ao method imprimirResultado percorrer toda a lista sendo ela ordenada ou não

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
        
        // Acredito que tempos que Chamar os métodos de VerificarRepetidos e Sorteio aqui
        // Antes do while Para não trocar o Processo caso empate com um já dentro da CPU
        Processo anterior = null;
        int indexAt= -1;

        while (!listaProcessos.isEmpty()) { //Enquanto a lista não for vazia vai executar, melhor do que pegar com base na quantidade total de processamento.

            Processo menor = null;
            Integer menorExecucao = Integer.MAX_VALUE;
            int index = -1;// — 1, pois 0 se refere a uma posição real dentro da lista e −1 não se refere, sendo melhor para não se referir a nenhum ao se inicializar e permitir a alteração ao definir um primeiro processo.
            if (anterior != null) {
                menor = anterior;
                index = indexAt;
            }
            for (int j = 0; j < listaProcessos.size(); j++) {
                Processo atual = listaProcessos.get(j); // melhora o entendimento já que se refere ao processo atual de indice j no lugar de usar listaProcessos(j).variável a ser usada
                if (atual.tempoChegada <= tempoAtual) {
                    if (atual.tempoExecucao < menorExecucao) {
                        menorExecucao = atual.tempoExecucao;
                    }

                }
            }
            ArrayList<Integer> possiveis = new ArrayList<>();

            for (int j = 0; j < listaProcessos.size(); j++) {
                Processo p = listaProcessos.get(j);

                if (p.tempoChegada <= tempoAtual && p.tempoExecucao == menorExecucao) {
                    possiveis.add(j);
                }
            }
            Random rand = new Random();
            int escolhido = possiveis.get(rand.nextInt(possiveis.size()));

            menor = listaProcessos.get(escolhido);
            index = escolhido;



            if (menor == null) {// ninguém chegou ainda caso nenhum processo tenha chegado a esse tempo todos acima desse tempo de processamento.
                tempoAtual++; // Aumenta o tempo atual
                // e entra na próxima execução do laço "continue" não executando o que está abaixo
                continue;
            }

            System.out.print(menor.nome + " " + menor.tempoChegada + " " + menor.tempoExecucao + " // ");

            menor.tempoExecucao--; // reduz o tempo de processamento/execução do processo para que ele seja concluído

            if (menor.tempoExecucao <= 0) {
                listaProcessos.remove(index);//Após concluído ele e removido da fila
                anterior = null;
            } else{
                anterior = menor;
                indexAt = index;
            }

            tempoAtual++;//aumenta em qual tempo de processamento a cpu esta
        }
    }

    public int Sorteio() {
        ArrayList<Integer> temposJaSorteados = new ArrayList<>();

        for (Processo listaProcesso : listaProcessos) {
            int tempoRepetido = listaProcesso.tempoChegada;

            if (!temposJaSorteados.contains(tempoRepetido)) {
                ArrayList<Integer> posicoesEncontradas = buscarRepetidos(tempoRepetido);

                if (posicoesEncontradas.size() > 1) {
                    Random sorteado = new Random();
                    int indiceSorteado = sorteado.nextInt(posicoesEncontradas.size());

                    return posicoesEncontradas.get(indiceSorteado);
                }

                temposJaSorteados.add(tempoRepetido);
            }
        }
        return -1; // Caso não encontre nenhum repetido, retorna -1
    }
    public ArrayList<Integer> buscarRepetidos(int tempoRepetido) {
        // Aqui é um método Tipo Arraylist porque retorna uma lista das posições
        // exemplo processo 1 e 3 tem tempos iguais logo na posição 0 dessa lista
        // de posições estará o 1 e na posição 1 o 3

        ArrayList<Integer> posicoes = new ArrayList<>();

        // Percorre a lista toda procurando quem tem o tempo igual ao "tempoRepetido"
        for (int i = 0; i < listaProcessos.size(); i++) {
            if (listaProcessos.get(i).tempoChegada == tempoRepetido) {
                posicoes.add(i); // Guarda o índice (0, 1, 2...)
            }
        }

        return posicoes; // Devolve a lista com as posições encontradas
    }

}



