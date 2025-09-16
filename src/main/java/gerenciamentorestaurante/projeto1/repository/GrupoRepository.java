package gerenciamentorestaurante.projeto1.repository;

import gerenciamentorestaurante.projeto1.entities.Grupo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
  @Modifying
  @Transactional
  @Query("UPDATE Grupo g SET g.status = -1 " +
      "WHERE g.id = :id")
  void apagarLogicoGrupo(@Param("id") Integer grupoId) ;

  @Query("SELECT g FROM Grupo g WHERE g.status>=0")
  List<Grupo> listarGrupos();

  @Query("SELECT g FROM Grupo g WHERE g.status>=0 AND g.tipo=0")
  Grupo buscarGrupoPadrao();

  @Query("SELECT g FROM Grupo g WHERE g.id = :id AND g.status>=0")
  Grupo listarGrupoPorID(@Param("id") Integer grupoId);

  @Query("SELECT g FROM Grupo g WHERE g.tipo=1  AND g.status>=0" )
  List<Grupo> buscarGrupoDeIngredientes();

  @Query("SELECT g FROM Grupo g WHERE g.id =:id AND g.status>=0 AND g.tipo=1")
  Grupo  buscarGrupoDeIngredientePorId(@Param("id") Integer grupoId);

  @Query("SELECT g FROM Grupo g WHERE g.tipo=2 AND g.status>=0 ")
  List<Grupo> buscarGrupoDeFichaTecnicas();

  @Query("SELECT g FROM Grupo g WHERE g.id =:id AND g.status>=0 AND g.tipo=2")
  Grupo  buscarGrupoDeFichaTecnicaPorId(@Param("id") Integer grupoId);


}
