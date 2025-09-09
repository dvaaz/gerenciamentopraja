package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entitites.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
