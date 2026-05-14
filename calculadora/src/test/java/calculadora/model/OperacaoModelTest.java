package calculadora.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OperacaoModelTest {

    @Test
    void soma() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(10.0);
        op.setNumber2(5.0);

        op.soma();

        assertEquals(15.0, op.getResultado(), "A soma de 10 + 5 deve ser 15");
    }

    @Test
    void subtracao() {
    }

    @Test
    void divisao() {
    }

    @Test
    void multiplicacao() {
    }

    @Test
    void porcentagem() {
    }

    @Test
    void raizQuadrada() {
    }

    @Test
    void potencia() {
    }
}