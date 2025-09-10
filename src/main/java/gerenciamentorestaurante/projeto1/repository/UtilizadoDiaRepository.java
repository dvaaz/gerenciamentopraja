package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entitites.UtilizadoDia;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilizadoDiaRepository extends JpaRepository<UtilizadoDia, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE UtilizadoDia p SET p.status = -1 " +
      "WHERE p.id = :id")
  void apagarLogicoUtilizadoDia(@Param("id") Integer utilizadoDiaId) ;

  @Query("SELECT p FROM UtilizadoDia p WHERE p.status>=0")
  List<UtilizadoDia> listarUtilizadoDias();

  @Query("SELECT p FROM UtilizadoDia p WHERE p.status = :id AND p.status>=0")
  UtilizadoDia buscarUtilizadoDiaPorID(@Param("id") Integer utilizadoDiaId);

}
