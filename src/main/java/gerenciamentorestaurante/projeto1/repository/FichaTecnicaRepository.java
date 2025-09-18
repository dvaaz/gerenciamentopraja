package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import org.springframework.transaction.annotation.Transactional;
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
  void apagarLogicoFichaTecnica(@Param("id") Integer fichaTecnicaId) ;

  @Query("SELECT f FROM FichaTecnica f WHERE f.status>=0")
  List<FichaTecnica> listarFichaTecnicas();

  @Query("SELECT f FROM FichaTecnica f WHERE f.id = :id AND f.status>=0")
  FichaTecnica listarFichaTecnicaPorID(@Param("id") Integer fichaTecnicaId);
}
