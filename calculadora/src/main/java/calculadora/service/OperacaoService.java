package calculadora.service;

import calculadora.model.OperacaoModel;
import calculadora.model.TipoCalculo;
import calculadora.repository.OperacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperacaoService {

    private final OperacaoRepository operacaoRepository;

    public OperacaoService(OperacaoRepository operacaoRepository) {
        this.operacaoRepository = operacaoRepository;
    }

    public List<OperacaoModel> mostrarOperacoes(){
        return operacaoRepository
                .findAll();
    }

    public OperacaoModel calcular(OperacaoModel operacacao){

        switch (operacacao.getTipoCalculo()){

            case SOMA -> operacacao.soma();
            case SUBTRACAO -> operacacao.subtracao();
            case MULTIPLICACAO -> operacacao.multiplicacao();
            case DIVISÃO -> operacacao.divisao();
            case PORCENTAGEM -> operacacao.porcentagem();
            case RAIZ_QUADRADA -> operacacao.raizQuadrada();
            case POTENCIACAO -> operacacao.potencia();
            default -> throw new IllegalArgumentException("Operação não suportada");
        }

        return operacaoRepository.save(operacacao);

    }

    public void deletarOperacao(Long id){
        OperacaoModel operacaoModel = operacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operação não encontrada para o ID: " + id));

        operacaoRepository.deleteById(id);

    }
}
