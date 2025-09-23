package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.dto.request.shared.ChangeToAnotherGrupoInBatchDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoInBatchDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingrediente.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.ingrediente.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingrediente")
@Tag(name ="Ingrediente", description="Api para  gerenciamento de ingredientes")
public class IngredienteContoller {
    private final IngredienteService ingredienteService;
    public IngredienteContoller (IngredienteService ingredienteService){
        this.ingredienteService = ingredienteService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Registrar ingrediente", description = "Endpoint para a criacao de novo objeto ingrediente")
    public ResponseEntity<IngredienteDTOResponse> criarIngrediente(@Valid @RequestBody IngredienteDTORequest ingrediente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.criarIngrediente(ingrediente));
    }

    @GetMapping("/listar")
    @Operation(summary = "listar todos os ingredientes", description = "Endpoint para listar todos os ingredientes")
    public ResponseEntity<List<Ingrediente>> listarIngredientes() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }

    @GetMapping("/buscar/{ingredienteId}")
    @Operation(summary = "listar ingrediente por id", description = "Endpoint para listar um ingrediente")
    public ResponseEntity<Ingrediente> buscarIngredientePorId(@Valid @PathVariable("ingredienteId") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if  (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.buscarIngredientePorId(ingredienteId));
        }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PatchMapping("/descricao/{ingredienteId}")
    @Operation(summary = "altera a descricao de um ingrediente", description = "Endpoint para alterar a descricao de um ingrediente")
    public ResponseEntity<UpdateDescricaoResponse> alterarDescricaoIngrediente(
            @Valid
            @PathVariable("ingredienteId") Integer ingredienteId,
            @RequestBody UpdateDescricaoRequest updateDescricaoRequest) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.atualizarDescricaoIngrediente(ingredienteId, updateDescricaoRequest));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PatchMapping("/status/{ingredienteId}")
    @Operation(summary = "altera status de um ingrediente", description = "Endpoint para alterar o status de um ingrediente")
    public ResponseEntity<UpdateStatusResponse> alterarStatusIngrediente(
            @Valid
            @PathVariable("ingredienteId") Integer ingredienteId,
            @RequestBody UpdateStatusRequest updateStatusRequest) {
        Ingrediente ingrediente =ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.atualizarStatusIngrediente(ingredienteId, updateStatusRequest));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // recebe Json com a lista de ingredientes e o número do grupo para o qual elas irao
    @PatchMapping("/grupo/{grupoId}/alteraremlista")
    @Operation(summary = "alterar o grupo de vários ingredientes", description="Endpoint para alterar o grupo de uma lista de ingredientes")
    public ResponseEntity<ChangeToAnotherGrupoInBatchDTOResponse> alterarGrupoListaDeIngredientes(
        @Valid @PathVariable("grupoId") Integer grupoId,
        @Valid @RequestBody ChangeToAnotherGrupoInBatchDTORequest dtoRequest
    ) {
        ChangeToAnotherGrupoInBatchDTOResponse ingredientesEmNovoGrupo = ingredienteService.alterarGrupoListaDeIngredientes(
                grupoId, dtoRequest.getIdDosItens());
        return ResponseEntity.ok(ingredientesEmNovoGrupo);
    }

    @PatchMapping("/grupo/{ingredienteId}")
    @Operation(summary = "alterar o grupo de um ingrediente", description = "Endpoint para alterar o grupo ao qual um ingrediente pertence")
    public ResponseEntity<ChangeToAnotherGrupoDTOResponse> alterarGrupoIngrediente(
            @Valid
            @PathVariable("ingredienteId") Integer ingredienteId,
            @RequestBody Integer novoGrupo) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.alterarGrupoIngrediente(ingredienteId, novoGrupo));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/deletar/{ingredienteId}")
    @Operation(summary = "remove o ingrediente", description = "Endpoint para remoção lógica")
    public ResponseEntity removerGrupoIngrediente(
            @Valid
            @PathVariable("ingredienteId") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            this.ingredienteService.apagarLogicoIngrediente(ingredienteId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/destroy/{ingredienteId}")
    @Operation(summary = "destroi o ingrediente", description = "Endpoint para destruir o ingrediente")
    public ResponseEntity removerIngrediente(
            @Valid
            @PathVariable("ingredienteId") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            this.ingredienteService.deletarDefinitivoIngrediente(ingredienteId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
