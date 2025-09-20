package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.IngredienteFichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica.AlterarMedidasIngredienteFichaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica.IngredienteFichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.AlterarMedidasIngredienteFichaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.ingredienteFichaTecnica.IngredienteFichaTecnicaDTOResponse;
import gerenciamentorestaurante.projeto1.service.IngredienteFichaTecnicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/ingredientefichatecnica")
@Tag(name="Detalhes da Ficha Tecnica", description = "Api para gerenciamento de 'ingrediente ficha tecnica'")
public class IngredienteFichaTecnicaController {
  private final IngredienteFichaTecnicaService service;
  public IngredienteFichaTecnicaController( IngredienteFichaTecnicaService service) {
    this.service = service;
  }

  @PostMapping("/criar")
  @Operation(summary = "Registro de detalhes da Ficha Tecnica", description = "Endpoint para a criacao de uma nova entrada de Ingrediente Ficha Tecnica")
  public ResponseEntity<IngredienteFichaTecnicaDTOResponse> criarRelacaoIngredienteFichaTecnica(
      @Valid @RequestBody IngredienteFichaTecnicaDTORequest dto
  ) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.criarRelacaoIngredienteFichaTecnica(dto));
  }

  @GetMapping("/listar/{id}/ingredientes")
  @Operation(summary = "Lista Ingredientes em Ficha Tecnica", description = "Endpoint para listar os ingredientes, unidade de medida e quantidade de cada um deles em uma ficha tecnica")
  public ResponseEntity<IngredienteFichaTecnica>
  @PatchMapping("/alterar/{id}/medidas")
  @Operation(summary = "Alteração de unidade de medida e quantidade", description = "Endpoint para alteração dos detalhes da medida da ficha tecnica")
  public ResponseEntity<AlterarMedidasIngredienteFichaDTOResponse> alterarMedidasIngredienteFichaTecnica(
      @Valid @PathVariable Integer id,
      @Valid @RequestBody AlterarMedidasIngredienteFichaDTORequest dto
  ) {
    return ResponseEntity.ok(service.alterarMedidasIngredienteFicha(id, dto));
  }

}
