package gerenciamentorestaurante.projeto1.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "utilizado_dia")
public class UtilizadoDia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "utilizado_dia_id")
  private int id;

  @Column(name = "utilizado_dia_quantidade")
  private int quantidade;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="ingrediente_receita", nullable = false)
  private IngredienteReceita ingredienteReceita;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="producao_dia", nullable = false)
  private ProducaoDia producaoDia;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="disponivel_dia_detalhe", nullable = false)
  private DisponivelDiaDetalhe disponivelDiaDetalhe;

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

  public IngredienteReceita getIngredienteReceita() {
    return ingredienteReceita;
  }

  public void setIngredienteReceita(IngredienteReceita ingredienteReceita) {
    this.ingredienteReceita = ingredienteReceita;
  }

  public ProducaoDia getProducaoDia() {
    return producaoDia;
  }

  public void setProducaoDia(ProducaoDia producaoDia) {
    this.producaoDia = producaoDia;
  }

  public DisponivelDiaDetalhe getDisponivelDiaDetalhe() {
    return disponivelDiaDetalhe;
  }

  public void setDisponivelDiaDetalhe(DisponivelDiaDetalhe disponivelDiaDetalhe) {
    this.disponivelDiaDetalhe = disponivelDiaDetalhe;
  }
}
