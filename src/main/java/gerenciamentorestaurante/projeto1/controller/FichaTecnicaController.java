package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.FichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.*;
import gerenciamentorestaurante.projeto1.service.FichaTecnicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fichatecnica")
@Tag(name="Ficha Tecnica", description="Api para gerenciamento de fichas tecnicas")
public class FichaTecnicaController {
  private final FichaTecnicaService fichaTecnicaService;
  public FichaTecnicaController (FichaTecnicaService fichaTecnicaService){
    this.fichaTecnicaService = fichaTecnicaService;
  }


  @PostMapping("/criar")
  @Operation(summary = "Registro de novo fichaTecnica", description = "Endpoint para a criacao de novo objeto fichaTecnica")
  public ResponseEntity<FichaTecnicaDTOResponse> criarFichaTecnica(@Valid @RequestBody FichaTecnicaDTORequest fichaTecnica) {
    return ResponseEntity.status(HttpStatus.CREATED).body(fichaTecnicaService.criarFichaTecnica(fichaTecnica));
  }

  @GetMapping("/listar")
  @Operation(summary = "listar todos os fichaTecnicas", description = "Endpoint para listar todos os fichaTecnicas")
  public ResponseEntity<List<FichaTecnica>> listarFichaTecnicas() {
    return ResponseEntity.ok(fichaTecnicaService.listarFichaTecnicas());
  }

  @GetMapping("/listar/{fichaTecnicaId}")
  @Operation(summary = "listar fichaTecnica por id", description = "Endpoint para listar um fichaTecnica")
  public ResponseEntity<FichaTecnica> listarFichaTecnicaPorId(@Valid @PathVariable("fichaTecnicaId") Integer fichaTecnicaId) {
    FichaTecnica fichaTecnica = fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if  (fichaTecnica != null) {
      return ResponseEntity.ok(fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId));
    }
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

  }

  @PatchMapping("{fichaTecnicaId}/descricao")
  @Operation(summary = "altera a descricao de um fichaTecnica", description = "Endpoint para alterar a descricao de um fichaTecnica")
  public ResponseEntity<UpdateDescricaoResponse> alterarDescricaoFichaTecnica(
      @Valid
      @PathVariable("fichaTecnicaId") Integer fichaTecnicaId,
      @RequestBody UpdateDescricaoRequest updateDescricaoRequest) {
    FichaTecnica fichaTecnica = fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if (fichaTecnica != null) {
      return ResponseEntity.ok(fichaTecnicaService.atualizarDescricaoFichaTecnica(fichaTecnicaId, updateDescricaoRequest));
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

  }

  @PatchMapping("{fichaTecnicaId}/status")
  @Operation(summary = "altera status de um fichaTecnica", description = "Endpoint para alterar o status de um fichaTecnica")
  public ResponseEntity<UpdateStatusResponse> alterarStatusFichaTecnica(
      @Valid
      @PathVariable("fichaTecnicaId") Integer fichaTecnicaId,
      @RequestBody UpdateStatusRequest updateStatusRequest) {
    FichaTecnica fichaTecnica =fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if (fichaTecnica != null) {
      return ResponseEntity.ok(fichaTecnicaService.atualizarStatusFichaTecnica(fichaTecnicaId, updateStatusRequest));
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @PatchMapping("/grupo/{fichaTecnicaId}")
  @Operation(summary = "alterar o grupo de um fichaTecnica", description = "Endpoint para alterar o grupo ao qual um fichaTecnica pertence")
  public ResponseEntity<ChangeToAnotherGrupoDTOResponse> alterarGrupoFichaTecnica(
      @Valid
      @PathVariable("fichaTecnicaId") Integer fichaTecnicaId,
      @RequestBody Integer novoGrupo) {
    FichaTecnica fichaTecnica = fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if (fichaTecnica != null) {
      return ResponseEntity.ok(fichaTecnicaService.alterarGrupoFichaTecnica(fichaTecnicaId, novoGrupo));
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @DeleteMapping("/deletar/{fichaTecnicaId}")
  @Operation(summary = "remove o fichaTecnica", description = "Endpoint para remoção lógica")
  public ResponseEntity removerGrupoFichaTecnica(
      @Valid
      @PathVariable("fichaTecnicaId") Integer fichaTecnicaId) {
    FichaTecnica fichaTecnica = fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if (fichaTecnica != null) {
      this.fichaTecnicaService.apagarLogicoFichaTecnica(fichaTecnicaId);
      return ResponseEntity.noContent().build();
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @DeleteMapping("/destroy/{fichaTecnicaId}")
  @Operation(summary = "destroi o fichaTecnica", description = "Endpoint para destruir o fichaTecnica")
  public ResponseEntity removerFichaTecnica(
      @Valid
      @PathVariable("fichaTecnicaId") Integer fichaTecnicaId) {
    FichaTecnica fichaTecnica = fichaTecnicaService.listarFichaTecnicaPorId(fichaTecnicaId);
    if (fichaTecnica != null) {
      this.fichaTecnicaService.deletarDefinitivoFichaTecnica(fichaTecnicaId);
      return ResponseEntity.noContent().build();
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }
}
