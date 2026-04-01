import src.EscalonadorSRT;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    EscalonadorSRT escalonador = new EscalonadorSRT();
    escalonador.adicionarProcesso("a", 1, 10);
    escalonador.adicionarProcesso("b", 1, 2);
    escalonador.adicionarProcesso("c", 2, 1);
    escalonador.adicionarProcesso("d", 3, 3);
    escalonador.adicionarProcesso("e", 4, 4);
    escalonador.adicionarProcesso("f", 5, 2);
    escalonador.adicionarProcesso("g", 6, 1);
    escalonador.adicionarProcesso("h", 7, 6);
    escalonador.adicionarProcesso("i", 8, 2);
    escalonador.adicionarProcesso("j", 9, 1);

    //escalonador.ordenarPorTempoChegada();
    escalonador.imprimirResultado();

}
