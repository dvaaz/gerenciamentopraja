package gerenciamentorestaurante.projeto1.repositories;

import gerenciamentorestaurante.projeto1.entitites.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
