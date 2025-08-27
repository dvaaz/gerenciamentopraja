package gerenciamentorestaurante.projeto1.entitites;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "receita")
public class Receita {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="receita_id")
  private int id;
  @Column(name="receita_nome")
  private String nome;
  @Column(name="receita_descricao")
  private String descricao;

  @OneToMany(mappedBy = "receita")
  private Set<IngredienteReceita> ingredienteReceitas;
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

  public Set<IngredienteReceita> getIngredienteReceitas() {
    return ingredienteReceitas;
  }

  public void setIngredienteReceitas(Set<IngredienteReceita> ingredienteReceitas) {
    this.ingredienteReceitas = ingredienteReceitas;
  }
}
