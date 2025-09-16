package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateGrupoDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.GrupoDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.GrupoAtualizarDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.GrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
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
    return ResponseEntity.ok(grupoService.listarGrupos());
  }

    @GetMapping("listar/ingrediente")
    @Operation(summary = "Listar grupos de ingredientes", description="Endpoint para obter apenas grupos de ingredientes")
    public ResponseEntity<List<Grupo>> listarGruposDeIngredientes() {
        return ResponseEntity.ok(grupoService.listarGruposDeIngredientes());
    }

    @GetMapping("listar/fichatecnica")
    @Operation(summary = "Listar grupos de fichatecnicas", description="Endpoint para obter apenas grupos de fichatecnicas")
    public ResponseEntity<List<Grupo>> listarGruposDeFichaTecnicas() {
        return ResponseEntity.ok(grupoService.listarGruposDeFichaTecnicas());
    }

  @GetMapping("/listar/{grupoId}")
    @Operation(summary = "Buscar um grupo", description ="Endpoint para obter um grupo por id")
    public ResponseEntity<Grupo> buscarGrupoPorId(@Valid @PathVariable Integer grupoId) {
      Grupo grupo = this.grupoService.listarGrupoPorID(grupoId);
      if(grupo != null){
          return ResponseEntity.ok(grupo);
      } else return ResponseEntity.notFound().build();
  }

    @PatchMapping("/alterar/{grupoId}")
    @Operation(summary = "Alterações em um grupo", description="Endpoint para alterar nome e cor de um grupo")
    public ResponseEntity<GrupoAtualizarDTOResponse> atualizarGrupo(
            @Valid @PathVariable Integer grupoId,
            @RequestBody UpdateGrupoDTORequest atualizarDTORequest) {
      GrupoAtualizarDTOResponse atualizado = grupoService.atualizarGrupo(grupoId, atualizarDTORequest);
      if (atualizado != null){
          return ResponseEntity.ok(atualizado);
      } else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/alterarstatus/{grupoId}")
    @Operation(summary = "Atualizaçao de grupo", description = "Endpoint para atualização lógica de um grupo")
    public ResponseEntity<UpdateStatusResponse> atualizarStatus(
            @Valid @PathVariable Integer grupoId,
            @RequestBody UpdateStatusRequest novoStatus
    ) {
      UpdateStatusResponse  statusResponse = grupoService.atualizarStatusGrupo(grupoId, novoStatus);
      if (statusResponse != null){
          return ResponseEntity.ok(statusResponse);
      } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletar/{grupoId}")
    @Operation(summary = "Deletar o grupo", description ="Endpoint para  a deleção lógica")
  public ResponseEntity deletarGrupoPorID(
      @Valid @PathVariable Integer grupoId) {
    this.grupoService.apagarGrupo(grupoId);
    return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{grupoId}/destroy")
  @Operation(summary = "Remover grupo", description = "Endpoint para destruir grupo")
  public ResponseEntity removerGrupoPorID(
      @Valid @PathVariable Integer grupoId
    ) {
    this.grupoService.destruiGrupo(grupoId);
    return ResponseEntity.noContent().build();
    }
}
