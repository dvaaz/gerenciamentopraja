package gerenciamentorestaurante.projeto1.entitites;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "fichaTecnica")
public class FichaTecnica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="ficha_tecnica_id")
  private int id;
  @Column(name="ficha_tecnica_nome")
  private String nome;
  @Column(name="ficha_tecnica_descricao")
  private String descricao;
  @Column(name="ficha_tecnica_status")
  private int status;
  @OneToMany(mappedBy = "fichaTecnica")
  private Set<IngredienteFichaTecnica> ingredienteFichaTecnicas;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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
