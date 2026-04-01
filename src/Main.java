import src.EscalonadorSRT;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    EscalonadorSRT escalonador = new EscalonadorSRT();
    escalonador.adicionarProcesso("a", 0, 10);
    escalonador.adicionarProcesso("b", 1, 1);
    escalonador.adicionarProcesso("c", 2, 1);
    escalonador.adicionarProcesso("d", 3, 1);
    escalonador.adicionarProcesso("e", 4, 1);
    escalonador.adicionarProcesso("f", 5, 1);
    escalonador.adicionarProcesso("g", 6, 1);
    escalonador.adicionarProcesso("h", 7, 1);
    escalonador.adicionarProcesso("i", 8, 1);
    escalonador.adicionarProcesso("j", 9, 1);

    escalonador.ordenarPorTempoChegada();
    escalonador.imprimirResultado();

}
