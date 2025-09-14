package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FichaTecnicaRepository extends JpaRepository<FichaTecnica, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE FichaTecnica f SET f.status = -1 " +
      "WHERE f.id = :id")
  void apagarLogicoCategoria(@Param("id") Integer categoriaId) ;

  @Query("SELECT f FROM FichaTecnica f WHERE f.status>=0")
  List<FichaTecnica> listarCategorias();

  @Query("SELECT f FROM FichaTecnica f WHERE f.status = :id AND f.status>=0")
  FichaTecnica buscarCategoriaPorID(@Param("id") Integer categoriaId);
}
