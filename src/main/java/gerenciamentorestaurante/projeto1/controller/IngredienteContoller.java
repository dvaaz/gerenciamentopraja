package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ingredientes")
@Tag(name ="Ingrediente", description="Api para  gerenciamento de ingredientes")
public class IngredienteContoller {
    private final IngredienteService ingredienteService;
    public IngredienteContoller (IngredienteService ingredienteService){
        this.ingredienteService = ingredienteService;
    }

    @GetMapping("/registrar")
    @Operation(summary = "Registro de novo ingrediente", description = "Endpoint para a criacao de novo objeto ingrediente")
    public ResponseEntity<IngredienteDTOResponse> registrarIngrediente(@Valid @RequestBody IngredienteDTORequest ingredienteDTORequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredienteService.registrarIngrediente(ingrediente));
    }

    @GetMapping("/listar")
    @Operation(summary = "listar todos os ingredientes", description = "Endpoint para listar todos os ingredientes")
    public ResponseEntity<List<IngredienteDTOResponse>> listarIngredientes() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }
    
}
