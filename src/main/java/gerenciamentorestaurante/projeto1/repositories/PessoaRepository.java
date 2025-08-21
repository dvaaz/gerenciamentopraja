package gerenciamentorestaurante.projeto1.repositories;

import gerenciamentorestaurante.projeto1.entitites.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Integer> {

}
