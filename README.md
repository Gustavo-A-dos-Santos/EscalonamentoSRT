# Escalonador de Processos — Algoritmo SRT (Shortest Remaining Time)

### 1. Estrutura de Dados e Gerenciamento de Entrada

Para que o escalonador funcione, ele precisa de uma forma dinâmica de armazenar e manipular os processos. Nesta etapa, definimos como os dados entram no sistema.

<img width="837" height="205" alt="image" src="https://github.com/user-attachments/assets/4ef930b0-fb56-42b3-b5a8-80d3cec4583a" />



#### Explicação Técnica:
* **`ArrayList<Processo>`**: Escolhi a `ArrayList` por ser uma estrutura de dados dinâmica que permite o crescimento flexível da fila de processos conforme o usuário faz as inserções no menu.
* **`adicionarProcesso()`**: Este método atua como a interface entre o Menu e a lógica interna. Ele recebe os parâmetros brutos (nome, chegada e execução), encapsula-os em um novo objeto da classe `Processo` e o armazena na lista para que o escalonador possa processá-los posteriormente.
### 2. Ciclo de Execução e Gerenciamento de Tempo

Este trecho inicia a simulação do processamento. É aqui que o escalonador decide o que fazer a cada segundo ("tic-tac") do relógio da CPU.

<img width="689" height="491" alt="image" src="https://github.com/user-attachments/assets/796796aa-f1b1-438b-a720-d9ad03b1bb34" />


#### Explicação Técnica:
* **Controle de Tempo (`tempoAtual`)**: Variável fundamental que sincroniza a chegada dos processos com o relógio do sistema.
* **Lista de Histórico (`imprimirProcessos`)**: Uma `ArrayList<String>` criada especificamente para registrar a "assinatura" da CPU. Ela guarda qual processo utilizou o processador em cada unidade de tempo, permitindo a reconstrução do gráfico de Gantt ao final.
* **Tratamento de Ociosidade (`indexEscolhido == -1`)**: O código prevê momentos em que a CPU está ligada, mas nenhum processo chegou ainda. Nesses casos, o sistema registra um estado de ociosidade (marcado como `*`) e avança o tempo, garantindo que o programa não trave esperando por processos.
### 3. Lógica de Preempção e Otimização de Troca

Nesta etapa, o escalonador decide se deve manter o processo atual em execução ou realizar uma troca de contexto. Aqui também ocorre a atualização do estado do processo e o registro da execução.

<img width="743" height="556" alt="image" src="https://github.com/user-attachments/assets/5139d9a8-8212-45b4-970f-93706d80a1c4" />

#### Explicação Técnica:
* **Otimização de Troca de Contexto**: O código verifica se o processo que já estava na CPU (`anterior`) possui o mesmo tempo restante que o novo candidato selecionado. Caso positivo, o sistema mantém o processo atual. Isso evita trocas desnecessárias, que em sistemas reais consomem ciclos de processamento extras.
* **Processamento Unitário**: O comando `menor.tempoExecucao--` simula o uso de 1 unidade de tempo da CPU. 
* **Gerenciamento de Ciclo de Vida**: Ao atingir `tempoExecucao <= 0`, o processo é removido da `listaProcessos`. Caso contrário, ele é definido como o processo `anterior`, servindo de referência para a comparação no próximo ciclo do relógio.
* **Finalização do Escalonamento**: Após o esvaziamento da lista, o método chama a função `imprimir(imprimirProcessos)` para renderizar o histórico capturado.
### 4. Inteligência de Seleção e Desempate (SRT)

Este método é responsável pela tomada de decisão da CPU. Ele filtra os processos disponíveis e seleciona aquele que garante a maior eficiência para o sistema no momento.

<img width="547" height="436" alt="image" src="https://github.com/user-attachments/assets/acae40ea-467b-4326-ac3e-f9caa41d2739" />


#### Explicação Técnica:
* **Filtro de Chegada**: O algoritmo primeiro identifica quais processos já "nasceram" no sistema (`tempoChegada <= tempoAtual`), ignorando processos que só chegarão no futuro.
* **Busca pelo Menor Tempo**: É realizada uma varredura para encontrar o valor mínimo de tempo de execução restante entre os candidatos prontos.
* **Tratamento de Concorrência (Empate)**: Um ponto de destaque no código é a criação da lista `possiveis`. Se houver mais de um processo com o mesmo tempo mínimo, o sistema utiliza a classe **Random** para sortear qual deles será executado. Isso simula um comportamento de escalonamento dinâmico e evita que o sistema priorize sempre o primeiro processo inserido na lista.
### 5. Renderização do Gráfico de Gantt (Saída de Dados)

Após a conclusão de todos os ciclos de CPU, este método formata o histórico coletado em uma tabela temporal, permitindo a visualização clara da linha do tempo do escalonamento.

<img width="657" height="325" alt="image" src="https://github.com/user-attachments/assets/3f9f3d7e-9576-47d3-93d0-ab82a4a68594" />


#### Explicação Técnica:
* **Mapeamento Temporal**: O primeiro laço `for` percorre o tamanho da lista de histórico para imprimir os índices de tempo (0, 1, 2...). Isso cria o cabeçalho da nossa tabela.
* **Linha de Processos**: O segundo laço extrai os nomes dos processos (ou o caractere de ociosidade `*`) armazenados durante a execução, alinhando cada um com seu respectivo tempo no cabeçalho.
* **Visualização de Gantt**: O resultado final no console funciona como um **Gráfico de Gantt simplificado**, essencial para auditar se o escalonador realmente respeitou a regra do Shortest Remaining Time (SRT) ao longo de toda a simulação.

### 6. Interface de Usuário e Fluxo de Entrada

O método `iniciar` gerencia a interação com o usuário, permitindo o cadastro dinâmico de múltiplos processos antes de disparar o motor de escalonamento.

<img width="678" height="546" alt="image" src="https://github.com/user-attachments/assets/84ee89cb-ce0a-45f2-8ee2-9e8d86f7ff89" />

#### Explicação Técnica:
* **Laço `do-while`**: Utilizado para permitir que o usuário adicione quantos processos desejar de forma contínua. A condição de parada é baseada na resposta do usuário (`S/N`), conferindo flexibilidade ao simulador.
* **Integração**: Após a coleta, o método invoca `ec.imprimirResultado()`, que é o ponto de partida para toda a lógica de processamento que explicamos anteriormente.

---
### 7. Inicialização do Sistema: Main.java

O arquivo `Main.java` é o ponto de partida da nossa aplicação. Ele tem a função de organizar e dar o começo no programa, funcionando como o maestro do sistema. Suas principais responsabilidades são:

1. **Instanciar o Escalonador:** Prepara o motor que vai realizar os cálculos da lógica do Shortest Remaining Time (SRT).
2. **Ativar o Menu:** Chama a interface de usuário (`menu.iniciar()`) para abrir o console e permitir que o usuário cadastre os processos e interaja com o sistema.

<img width="455" height="153" alt="image" src="https://github.com/user-attachments/assets/aadb8e22-78da-43e1-91a0-88b9cc00d5b6" />


### 8. Validação e Sanitização de Dados

Um ponto crítico em qualquer software é a garantia de que os dados inseridos são válidos. Implementei métodos específicos para "limpar" e validar as entradas do teclado.

<img width="644" height="428" alt="image" src="https://github.com/user-attachments/assets/fce43d1b-0db7-4d8d-92ed-797c6cdb075f" />



#### Explicação Técnica:
* **Prevenção de Crash (`hasNextInt`)**: O código utiliza `sc.hasNextInt()` dentro de um laço `while`. Isso impede que o programa quebre caso o usuário digite uma letra onde deveria ser um número, limpando o buffer do Scanner automaticamente.
* **Consistência de Regras de Negócio**:
    * **Tempo de Chegada**: Validado para garantir que não existam tempos negativos (`>= 0`).
    * **Tempo de Execução (Burst)**: Validado para ser estritamente maior que zero (`> 0`), pois um processo sem tempo de execução não teria propósito no escalonador.
* **Loops Infinitos de Correção**: O uso de `while(true)` com `break` força o usuário a inserir um dado correto antes de prosseguir, garantindo que o objeto `Processo` seja criado com informações íntegras.

## 📊 Demonstração de Escalonamento

Cenário de teste utilizado para validação da lógica:
- **P1:** Chegada: 0 | Duração: 5
- **P2:** Chegada: 1 | Duração: 3

### Diagrama de Gantt
| Tempo | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| **CPU** | P1 | P2 | P2 | P2 | P1 | P1 | P1 | P1 |

**Análise técnica:** No instante $T=1$, o processo P2 entra no sistema. Como o tempo restante de P1 é 4 e o tempo total de P2 é 3, o sistema realiza a preempção de P1 em favor da execução de P2.
## 📂 Estrutura do Projeto

O software foi desenvolvido em **Java**, estruturado de forma modular seguindo princípios de POO:

- `Processo.java`: Encapsula os atributos básicos do processo.
- `EscalonadorSRT.java`: Implementa a lógica de decisão, gerenciamento de filas e contabilização de ciclos de CPU.
- `Menu.java`: Interface CLI para entrada de parâmetros e exibição de estatísticas de execução.
## 👩‍💻 Equipe de Desenvolvimento
* **Gustavo Antony**
* **Julia Gabriele**
* **Karine Vitória**
* **Lucas Santana**
