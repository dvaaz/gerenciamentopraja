package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.IngredienteFichaTecnica;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredienteFichaTecnicaRepository extends JpaRepository<FichaTecnica, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE IngredienteFichaTecnica f SET f.status = -1 " +
      "WHERE f.id = :id")
  void apagarLogicoIngredienteFichaTecnica(@Param("id") Integer ingredienteFichaTecnicaId) ;

  @Query("SELECT f FROM IngredienteFichaTecnica f WHERE f.status>=0")
  List<IngredienteFichaTecnica> listarIngredienteFichaTecnicas();

  @Query("SELECT f FROM IngredienteFichaTecnica f WHERE f.id = :id AND f.status>=0")
  IngredienteFichaTecnica buscarIngredienteFichaTecnicaPorID(@Param("id") Integer ingredienteFichaTecnicaId);

  @Query("SELECT x FROM IngredienteFichaTecnica x WHERE x.ingredienteId = :ingredienteId AND x.fichaTecnicaId = :fichaTecnicaId AND x.status>=0")
  IngredienteFichaTecnica buscarIngredienteExisteEmFichaTecnica(
      @Param("ingredienteId") Integer ingredienteId, @Param("fichaTecnicaId") Integer fichaTecnicaId);

}
