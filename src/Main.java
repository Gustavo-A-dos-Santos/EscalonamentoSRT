import src.EscalonadorSRT;
import src.Menu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    EscalonadorSRT escalonador = new EscalonadorSRT();

    // Adicionei o Menu deixado Comentado essa Parte Para Futuros Testes;

    escalonador.adicionarProcesso("A", 0, 2);
    escalonador.adicionarProcesso("B", 0, 2);
    escalonador.adicionarProcesso("C", 0, 2);
    escalonador.adicionarProcesso("D", 0, 2);

    escalonador.imprimirResultado();


    /*enu menu = new Menu();
    menu.iniciar();*/

}
