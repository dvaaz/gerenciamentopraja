package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import org.springframework.transaction.annotation.Transactional;
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

  @Query("SELECT i FROM Ingrediente i WHERE i.id = :id AND i.status>=0")
  Ingrediente buscarIngredientePorId(@Param("id") Integer ingredienteId);

  @Query("SELECT i FROM Ingrediente i WHERE i.id IN :ids AND i.status >= 0")
  List<Ingrediente> listarIngredientesEmLista(@Param("ids") List<Integer> ids);

  @Query("SELECT i FROM Ingrediente i WHERE i.grupo = :grupoId")
  List<Ingrediente> listarFichasIngredientesPorGrupo(@Param("grupoId") Integer grupo);
}
