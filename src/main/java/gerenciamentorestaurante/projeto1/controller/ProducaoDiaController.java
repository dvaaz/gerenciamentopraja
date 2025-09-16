package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.ProducaoDia;
import gerenciamentorestaurante.projeto1.entities.dto.request.ProducaoDiaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.ProducaoDiaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.service.ProducaoDiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producaodia")
@Tag(name="Produção do Dia", description= "Api para gerenciamento da tabela de producao dia")
public class ProducaoDiaController  {
    private final ProducaoDiaService producaoDiaService;

    public ProducaoDiaController(ProducaoDiaService producaoDiaService) {this.producaoDiaService= producaoDiaService;}

    @PostMapping("/criar")
    @Operation(summary="Registro de producao dia", description = "Endpoint para criar uma nova entrada de produçao do dia")
    public ResponseEntity<ProducaoDiaDTOResponse> criarProducaoDia (@Valid @RequestBody ProducaoDiaDTORequest producaoDia){
        return ResponseEntity.status(HttpStatus.CREATED).body(producaoDiaService.criarProducaoDia(producaoDia));
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar tabelas de Producao Diária", description = "Endpoint para obter todas as entries de producao dia")
    public ResponseEntity<List<ProducaoDia>> listarProducoesDia() {return ResponseEntity.ok(producaoDiaService.listarProducaoDias());}

    @GetMapping("/listar/{proDiaId}")
    @Operation(summary = "Buscar producao diária por ID", description = "Endpoint para obter UMA producao dia buscando por ID")
    public ResponseEntity<ProducaoDia> buscarProducaoDiaPorId(@Valid @PathVariable Integer proDiaId){
        ProducaoDia proDia = this.producaoDiaService.buscarProducaoDiaPorId(proDiaId);
        if(proDia != null){
            return ResponseEntity.ok(proDia);
        } else return  ResponseEntity.notFound().build();
    }

    @PatchMapping("/alterarstatus/{proDiaId}")
    @Operation(summary = "Alterar status de producao", description = "Endpoint para alteração de status de produção diária")
    public ResponseEntity<UpdateStatusResponse> atualizarStatus(
            @Valid @PathVariable Integer proDiaId,
            @RequestBody UpdateStatusRequest novoStatus
            ) {
        UpdateStatusResponse statusResponse = producaoDiaService.atualizarStatusProducaoDia(proDiaId, novoStatus);
        if (statusResponse != null) {
            return ResponseEntity.ok(statusResponse);
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deletar/{proDiaId}")
    @Operation(summary = "Deletar a Producao Dia", description = "Endpoint para a deleção lógica")
    public ResponseEntity deletarProducaoDiariaPorId( @Valid @PathVariable Integer proDiaId){
        this.producaoDiaService.apagarLogicoProducaoDia(proDiaId);
        return ResponseEntity.noContent().build();
    }
}
