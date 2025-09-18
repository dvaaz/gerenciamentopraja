package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.Producao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Producao p SET p.status = -1 " +
      "WHERE p.id = :id")
  void apagarLogicoProducao(@Param("id") Integer producaoId) ;

  @Query("SELECT p FROM Producao p WHERE p.status>=0")
  List<Producao> listarProducoes();

  @Query("SELECT p FROM Producao p WHERE p.id = :id AND p.status>=0")
  Producao listarProducaoPorID(@Param("id") Integer producaoId);

}
