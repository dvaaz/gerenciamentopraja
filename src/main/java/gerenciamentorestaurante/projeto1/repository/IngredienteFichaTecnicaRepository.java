package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entitites.FichaTecnica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredienteFichaTecnicaRepository extends JpaRepository<FichaTecnica, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE FichaTecnica f SET f.status = -1 " +
      "WHERE f.id = :id")
  void apagarLogicoFichaTecnica(@Param("id") Integer fichaTecnicaId) ;

  @Query("SELECT f FROM FichaTecnica f WHERE f.status>=0")
  List<FichaTecnica> listarFichaTecnicas();

  @Query("SELECT f FROM FichaTecnica f WHERE f.status = :id AND f.status>=0")
  FichaTecnica obterFichaTecnicaPorID(@Param("id") Integer fichaTecnicaId);

}
