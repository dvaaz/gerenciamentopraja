package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.Estoque;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Estoque e SET e.status = -1 " +
      "WHERE e.id = :id")
  void apagarLogicoEstoque(@Param("id") Integer estoqueId) ;

  @Query("SELECT e FROM Estoque e WHERE e.status>=0")
  List<Estoque> listarEstoques();

  @Query("SELECT e FROM Estoque e WHERE e.status = :id AND e.status>=0")
  Estoque buscarEstoquePorID(@Param("id") Integer estoqueId);
}
