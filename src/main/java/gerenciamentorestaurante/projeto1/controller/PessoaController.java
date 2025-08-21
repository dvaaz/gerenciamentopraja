package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entitites.Pessoa;
import gerenciamentorestaurante.projeto1.services.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pessoa")
public class PessoaController {

  private final PessoaService pessoaService;

  public PessoaController(PessoaService pessoaService) {
    this.pessoaService = pessoaService;
  }

  @GetMapping("/listar")
  public ResponseEntity<List<Pessoa>> listarUsuarios() {
    return ResponseEntity.ok(pessoaService.listarUsuarios());
  }
}
