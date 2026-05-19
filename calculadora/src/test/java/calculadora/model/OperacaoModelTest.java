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
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(10.0);
        op.setNumber2(4.0);

        op.subtracao();

        assertEquals(6.0, op.getResultado(), "A subtração de 10 - 4 deve ser 6");
    }

    @Test
    void divisao() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(10.0);
        op.setNumber2(2.0);

        op.divisao();

        assertEquals(5.0, op.getResultado(), "A divisão de 10 por 2 deve ser 5");
    }

    @Test
    void divisaoPorZeroDeveLancarExcecao() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(10.0);
        op.setNumber2(0.0);

        ArithmeticException exception = assertThrows(ArithmeticException.class, op::divisao,
                "Deveria lançar ArithmeticException ao dividir por zero");

        assertEquals("Divisão por zero não permitido", exception.getMessage());
    }

    @Test
    void multiplicacao() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(6.0);
        op.setNumber2(5.0);

        op.multiplicacao();

        assertEquals(30.0, op.getResultado(), "A multiplicação de 6 * 5 deve ser 30");
    }

    @Test
    void porcentagem() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(200.0);
        op.setNumber2(15.0);

        op.porcentagem();

        assertEquals(30.0, op.getResultado(), "15% de 200 deve ser 30");
    }

    @Test
    void raizQuadrada() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(25.0);

        op.raizQuadrada();

        assertEquals(5.0, op.getResultado(), "A raiz quadrada de 25 deve ser 5");
    }

    @Test
    void potencia() {
        OperacaoModel op = new OperacaoModel();
        op.setNumber1(2.0);
        op.setNumber2(3.0);

        op.potencia();

        assertEquals(8.0, op.getResultado(), "2 elevado a 3 deve ser 8");
    }
}