package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.entitites.Usuario;
import gerenciamentorestaurante.projeto1.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @GetMapping("/listar")
  public ResponseEntity<List<Usuario>> listarUsuarios() {
    return ResponseEntity.ok(usuarioService.listarUsuarios());
  }
}
