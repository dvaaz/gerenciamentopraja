package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateGrupoRequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateGrupoResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entitites.Grupo;
import gerenciamentorestaurante.projeto1.entitites.Ingrediente;
import gerenciamentorestaurante.projeto1.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ingredientes")
@Tag(name ="Ingrediente", description="Api para  gerenciamento de ingredientes")
public class IngredienteContoller {
    private final IngredienteService ingredienteService;
    public IngredienteContoller (IngredienteService ingredienteService){
        this.ingredienteService = ingredienteService;
    }

    @PostMapping("/criar")
    @Operation(summary = "Registro de novo ingrediente", description = "Endpoint para a criacao de novo objeto ingrediente")
    public ResponseEntity<IngredienteDTOResponse> criarIngrediente(@Valid @RequestBody IngredienteDTORequest ingrediente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.criarIngrediente(ingrediente));
    }

    @GetMapping("/listar")
    @Operation(summary = "listar todos os ingredientes", description = "Endpoint para listar todos os ingredientes")
    public ResponseEntity<List<Ingrediente>> listarIngredientes() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "listar ingrediente por id", description = "Endpoint para listar um ingrediente")
    public ResponseEntity<Ingrediente> listarIngredientePorId(@PathVariable("ingredienteId") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if  (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.buscarIngredientePorId(ingredienteId));
        }
            else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PatchMapping("{id}/descricao")
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

    @PatchMapping("{id}/status")
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

    @PatchMapping("{id}/grupo")
    @Operation(summary = "alterar o grupo de um ingrediente", description = "Endpoint para alterar o grupo ao qual um ingrediente pertence")
    public ResponseEntity<UpdateGrupoResponse> alterarGrupoIngrediente(
            @Valid
            @PathVariable("ingredienteID") Integer ingredienteId,
            @RequestBody UpdateGrupoRequest updateGrupoRequest) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            return ResponseEntity.ok(ingredienteService.alterarGrupoIngrediente(ingredienteId, updateGrupoRequest));
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "remove o ingrediente", description = "Endpoint para remoção lógica")
    public ResponseEntity removerGrupoIngrediente(
            @Valid
            @PathVariable("ingredienteID") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            this.ingredienteService.apagarLogicoIngrediente(ingredienteId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/destroy/{id}")
    @Operation(summary = "destroi o ingrediente", description = "Endpoint para destruir o ingrediente")
    public ResponseEntity removerIngrediente(
            @Valid
            @PathVariable("ingredienteID") Integer ingredienteId) {
        Ingrediente ingrediente = ingredienteService.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null) {
            this.ingredienteService.deletarDefinitivoIngrediente(ingredienteId);
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
