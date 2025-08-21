package gerenciamentorestaurante.projeto1.services;

import gerenciamentorestaurante.projeto1.entitites.Pessoa;
import gerenciamentorestaurante.projeto1.repositories.PessoaRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PessoaService {
  private final PessoaRepository pessoaRepository;

  public PessoaService(PessoaRepository pessoaRepository) {
    this.pessoaRepository = pessoaRepository;
  }

  public List<Pessoa> listarUsuarios() {
    return this.pessoaRepository.findAll();
}
}
