package calculadora.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoCalculo {
    SOMA("Soma"),
    SUBTRACAO("Subtração"),
    MULTIPLICACAO("Multiplicação"),
    DIVISÃO("Divisão"),
    PORCENTAGEM("Porcentagem"),
    POTENCIACAO("Potenciacão"),
    RAIZ_QUADRADA("Raiz quadrada");

    private String name;

    TipoCalculo(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static TipoCalculo fromName (String valor){
        for (TipoCalculo tipoCalculo : TipoCalculo.values()){
            if (tipoCalculo.name.equalsIgnoreCase(valor)){
                return tipoCalculo;
            }
        }
        throw new IllegalArgumentException("Tipo de cálculo inválido: "+valor );
    }
}
