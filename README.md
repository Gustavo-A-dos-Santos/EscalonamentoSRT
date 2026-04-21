# Escalonador de Processos — Algoritmo SRT (Shortest Remaining Time)

Este projeto implementa a lógica de escalonamento preemptivo **SRT**, focando na eficiência da CPU ao priorizar processos com menor tempo de execução restante.

---

## 🔍 Detalhamento da Implementação

Abaixo, apresento os principais trechos de código que compõem a lógica do sistema, com suas respectivas explicações funcionais.

### 1. Estrutura de Armazenamento e Adição
Para gerenciar os processos, utilizei a estrutura de `ArrayList`. O método de adição garante que cada "personagem" da nossa história seja encapsulado corretamente antes de entrar na fila.

![Captura de tela 2026-04-21 113435.png](../../OneDrive/Imagens/Screenshots/Captura%20de%20tela%202026-04-21%20113435.png)

* **Explicação:** O uso de `ArrayList` permite uma manipulação dinâmica, facilitando a remoção de processos conforme eles são finalizados.