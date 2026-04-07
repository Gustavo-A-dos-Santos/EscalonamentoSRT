package src;

import java.util.ArrayList;
import java.util.Random;

public class EscalonadorSRT {

    ArrayList<Processo> listaProcessos = new ArrayList<>();

    public void adicionarProcesso(String nome, int tempoChegada, int tempoExecucao) {
        listaProcessos.add(new Processo(nome, tempoChegada, tempoExecucao));
    }

    public void imprimirResultado() {

        int tempoAtual = 0;
        Processo anterior = null;
        int indexAt = -1;

        while (!listaProcessos.isEmpty()) {

            int indexEscolhido = escolherProcesso(tempoAtual);

            if (indexEscolhido == -1) {
                tempoAtual++;
                continue;
            }

            Processo menor;
            int index;

            //Recupera o tempo de execução restante do processo que o método escolherProcesso elegeu.
            int menorExecucao = listaProcessos.get(indexEscolhido).tempoExecucao;

            //Se já havia um processo rodando (anterior != null) E o tempo dele é IGUAL ao menor tempo encontrado
            if (anterior != null && anterior.tempoExecucao == menorExecucao) {
                menor = anterior;
                index = indexAt;
            } else {//Caso o processo novo for mais rápido ou se a CPU estava vazia é validado o novo processo vindo do sorteio ou escolha.
                menor = listaProcessos.get(indexEscolhido);
                index = indexEscolhido;
            }

            //timeline
            /*
            System.out.print(menor.nome + " " + menor.tempoChegada + " " + menor.tempoExecucao + " // ");
            System.out.println("tempo atual: " + tempoAtual);
            System.out.println("NA CPU: " + menor.nome + " (Restante: " + menor.tempoExecucao + ")");
            System.out.println("fila de espera");

            boolean existobj = false;//verificar se existe algum objeto na fila de espera;
            for(Processo p: listaProcessos ){
                if(p.tempoChegada <= tempoAtual && p != menor){
                    System.out.println("Nome: " + p.nome + "Tempo de Execução" + p.tempoExecucao);
                    existobj = true;//validar a existencia de algum objeto na fila;
                }
            }
            if(existobj == false){
                System.out.print("Fila de espera vazia.");
            }*/

            menor.tempoExecucao--;

            if (menor.tempoExecucao <= 0) {
                listaProcessos.remove(index);
                anterior = null;
            } else {
                anterior = menor;
                indexAt = index;
            }





            tempoAtual++;
        }
    }

    public int escolherProcesso(int tempoAtual) {
        int menorExecucao = Integer.MAX_VALUE; //menorExecucao com o maior valor possível para encontrar o mínimo real

        for (Processo atual : listaProcessos) {//processo de repetição  verificar qual o menor tempo entre quem já chegou
            if (atual.tempoChegada <= tempoAtual) {
                if (atual.tempoExecucao < menorExecucao) {
                    menorExecucao = atual.tempoExecucao;
                }
            }
        }

        ArrayList<Integer> possiveis = new ArrayList<>();//Cria uma lista de obj que empataram no menor tempo

        for (int i = 0; i < listaProcessos.size(); i++) {
            Processo p = listaProcessos.get(i);

            if (p.tempoChegada <= tempoAtual && p.tempoExecucao == menorExecucao) {
                possiveis.add(i);
            }
        }

        if (possiveis.isEmpty()) {
            return -1;
        }

        Random rand = new Random();
        return possiveis.get(rand.nextInt(possiveis.size()));
    }

}



