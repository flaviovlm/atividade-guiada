package calculadora.controller;

import calculadora.model.OperacaoModel;
import calculadora.service.OperacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
@CrossOrigin("*")
public class OperacaoController {

    private final OperacaoService operacaoService;

    public OperacaoController(OperacaoService operacaoService) {
        this.operacaoService = operacaoService;
    }

    @GetMapping()
    public ResponseEntity<List<OperacaoModel>> mostrarOperacoes(){
        return ResponseEntity.status(HttpStatus.OK).body(operacaoService.mostrarOperacoes());
    }

    @PostMapping
    public ResponseEntity<OperacaoModel> realizarCalculo(@RequestBody OperacaoModel operacao) {
        OperacaoModel operacaoSalva = operacaoService.calcular(operacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(operacaoSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOperacao(@PathVariable Long id) {
        operacaoService.deletarOperacao(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
