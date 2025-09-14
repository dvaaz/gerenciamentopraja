package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.Producao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducaoDiaRepository extends JpaRepository<Producao, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Producao p SET p.status = -1 " +
      "WHERE p.id = :id")
  void apagarLogicoProducao(@Param("id") Integer producaoId) ;

  @Query("SELECT p FROM Producao p WHERE p.status>=0")
  List<Producao> listarProducaos();

  @Query("SELECT p FROM Producao p WHERE p.status = :id AND p.status>=0")
  Producao buscarProducaoPorID(@Param("id") Integer producaoId);

}
