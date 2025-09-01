package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/listar")
    @Operation(summary = "listar todos os ingredientes", description = "Endpoint para listar todos os ingredientes")
    public ResponseEntity<List<IngredienteDTOResponse>> listarIngredientes() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }
    
}
