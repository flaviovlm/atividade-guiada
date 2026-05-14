package calculadora.model;

import jakarta.persistence.*;

@Entity(name = "Operacao")
public class OperacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double number1;
    @Column(nullable = true)
    private Double number2;
    @Column
    private Double resultado;
    @Enumerated
    @Column(name = "tipo_calculo")
    TipoCalculo tipoCalculo;

    public OperacaoModel() {
    }

    public OperacaoModel(Long id, Double number1, Double number2, Double resultado, TipoCalculo tipoCalculo) {
        this.id = id;
        this.number1 = number1;
        this.number2 = number2;
        this.resultado = resultado;
        this.tipoCalculo = tipoCalculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNumber1() {
        return number1;
    }

    public void setNumber1(Double number1) {
        this.number1 = number1;
    }

    public Double getNumber2() {
        return number2;
    }

    public void setNumber2(Double number2) {
        this.number2 = number2;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public TipoCalculo getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(TipoCalculo tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
    }

    public void soma (){
         this.resultado = this.number1 + this.number2;
    }
    public void  subtracao (){
        this.resultado = this.number1 - this.number2;
    }
    public void divisao (){
        if (this.number2 == 0) throw new ArithmeticException("Divisão por zero não permitido");
        this.resultado = this.number1 / this.number2;
    }
    public void multiplicacao (){
        this.resultado = this.number1 * this.number2;

    }
    public void porcentagem (){
        this.resultado = this.number1 * (this.number2/100);
    }
    public void raizQuadrada (){
         this.resultado = Math.sqrt(this.number1);
    }
    public void potencia(){
        this.resultado = Math.pow(this.number1, this.number2);
    }


}
