package gerenciamentorestaurante.projeto1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ficha_tecnica")
public class FichaTecnica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ficha_tecnica_id")
  private Integer id;
  @Column(name="ficha_tecnica_nome")
  private String nome;
  @Column(name="ficha_tecnica_descricao")
  private String descricao;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "grupo_id", nullable = false)
  private Grupo grupo;
  @Column(name="ficha_tecnica_status")
  private int status;
  @OneToMany(mappedBy = "fichaTecnicas")
  private Set<IngredienteFichaTecnica> ingredienteFichaTecnicas;

  public int getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Grupo getGrupo() {
    return grupo;
  }

  public void setGrupo(Grupo grupo) {
    this.grupo = grupo;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Set<IngredienteFichaTecnica> getIngredienteFichaTecnicas() {
    return ingredienteFichaTecnicas;
  }

  public void setIngredienteFichaTecnicas(Set<IngredienteFichaTecnica> ingredienteFichaTecnicas) {
    this.ingredienteFichaTecnicas = ingredienteFichaTecnicas;
  }
}
