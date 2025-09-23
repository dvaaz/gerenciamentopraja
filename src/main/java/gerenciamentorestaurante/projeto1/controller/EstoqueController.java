package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.Estoque;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueDTOResponse;
import gerenciamentorestaurante.projeto1.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estoque")
@Tag(name="Estoque", description="Api para gerenciamento de estoque")
public class EstoqueController {
  private final EstoqueService service;

  public EstoqueController(EstoqueService estoqueService) {this.service = estoqueService;}

  @PostMapping("/criar")
  @Operation(summary = "Registrar estoque", description="Endpoint para a criacao de entrada de estoque")
  public ResponseEntity<EstoqueDTOResponse> criarEstoque(
      @Valid @RequestBody EstoqueDTORequest dtoRequest
  ) {
    return ResponseEntity.ok(service.criarEstoque(dtoRequest));
  }

  @GetMapping("/listar")
  @Operation(summary = "Listar estoques",  description = "Endpoint para listar os estoques ativos")
  public ResponseEntity<List<Estoque>> listarEstoques() {
    return ResponseEntity.ok(service.listarEstoques());
  }
  
  @GetMapping("/buscar/{id}")
  @Operation(summary = "Buscar estoque", description = "Endpoint para buscar estoque por Id")
  public ResponseEntity<EstoqueDTOResponse> buscarEstoquePorId(
      @Valid @PathVariable Integer id
  ) {

  }
}
