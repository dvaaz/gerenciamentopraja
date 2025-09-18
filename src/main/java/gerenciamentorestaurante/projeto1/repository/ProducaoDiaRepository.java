package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.ProducaoDia;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProducaoDiaRepository extends JpaRepository<ProducaoDia, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE ProducaoDia p SET p.status = -1 " +
      "WHERE p.id = :id")
  void apagarLogicoProducaoDia(@Param("id") Integer producaoDiaId) ;

  @Query("SELECT p FROM ProducaoDia p WHERE p.status>=0")
  List<ProducaoDia> listarProducoesDia();

  @Query("SELECT p FROM ProducaoDia p WHERE p.id = :id AND p.status>=0")
  ProducaoDia listarProducaoDiaPorID(@Param("id") Integer producaoDiaId);

}
