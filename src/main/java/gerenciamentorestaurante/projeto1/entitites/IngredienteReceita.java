package gerenciamentorestaurante.projeto1.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="ingrediente_receita")
public class IngredienteReceita {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ingrediente_receita_id")
  private int id;
  @Column(name= "ingrediente_receita_quantidade")
  private int quantidade;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ingrediente_id", nullable = false)
  private Ingrediente ingrediente;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "receita_id", nullable = false)
  private Receita receita;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Receita getReceita() {
    return receita;
  }

  public void setReceita(Receita receita) {
    this.receita = receita;
  }
}
