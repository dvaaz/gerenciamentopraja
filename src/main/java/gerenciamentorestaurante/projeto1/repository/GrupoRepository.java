package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entitites.Grupo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Grupo g SET g.status = -1 " +
      "WHERE g.id = :id")
  void apagarLogicoGrupo(@Param("id") Integer grupoId) ;

  @Query("SELECT g FROM Grupo g WHERE g.status>=0")
  List<Grupo> listarGrupos();

  @Query("SELECT g FROM Grupo g WHERE g.status = :id AND g.status>=0")
  Grupo obterGrupoPorID(@Param("id") Integer grupoId);

  @Query("SELECT g FROM Grupo g WHERE g.status = :id AND g.status>=0 AND g.tipo=0")
  Grupo obterGrupoIngredientePorId(@Param("id") Integer grupoId);

  @Query("SELECT g FROM Grupo g WHERE g.status = :id AND g.status>=0 AND g.tipo=1")
  Grupo obterGrupoFichaTecnicaPorId(@Param("id") Integer grupoId);

}
