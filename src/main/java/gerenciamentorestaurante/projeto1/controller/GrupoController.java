package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.dto.request.GrupoDTORequest;
import gerenciamentorestaurante.projeto1.dto.response.GrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entitites.Grupo;
import gerenciamentorestaurante.projeto1.service.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/grupos")
@Tag(name="Grupo", description = "Api para gerenciamento de grupo")
public class GrupoController {

  private final GrupoService grupoService;

  public GrupoController(GrupoService grupoService){
    this.grupoService = grupoService;
  }

  @PostMapping("/criar")
  @Operation(summary = "Registro de novo grupo", description = "Endpoint para a criacao de novo objeto grupo")
  public ResponseEntity<GrupoDTOResponse> criarGrupo(@Valid @RequestBody GrupoDTORequest grupo) {
    return ResponseEntity.status(HttpStatus.CREATED).body(grupoService.criarGrupo(grupo));
  }

  @GetMapping("/listar")
  @Operation(summary ="Listar todos os grupos", description = "Endpoint para listar todos os grupos")
  public ResponseEntity<List<Grupo>> listarGrupos() {
    return ResponseEntity.ok(GrupoService.listarGrupos());
  }
}
