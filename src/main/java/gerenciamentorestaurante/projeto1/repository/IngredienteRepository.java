package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entitites.Ingrediente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Ingrediente i SET i.status = -1" +
      "WHERE i.id = :id")
  void apagarLogicoIngrediente(@Param("id") Integer ingredieneId);

  @Query("SELECT i FROM Ingrediente i WHERE i.status>=0")
  List<Ingrediente> listarIngredientes();

  @Query("SELECT i FROM Ingrediente i WHERE i.status = :id AND i.status>=0")
  Ingrediente obterIngredientePorID(@Param("id") Integer ingredienteId);
}
