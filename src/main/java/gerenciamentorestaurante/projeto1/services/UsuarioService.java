package gerenciamentorestaurante.projeto1.services;

import gerenciamentorestaurante.projeto1.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository =usuarioRepository;
  }

  public List<Usuario> listarUsuarios() {
    return this.usuarioRepository.findAll();
}
}
