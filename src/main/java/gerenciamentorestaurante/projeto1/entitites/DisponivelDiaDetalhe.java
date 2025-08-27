package gerenciamentorestaurante.projeto1.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "disponivel_dia_detalhe")
public class DisponivelDiaDetalhe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "disponivel_dia_detalhe_id")
  private int id;
  @Column(name = "disponivel_dia_detalhe_quantidade")
  private int quantidade;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "producao_dia_id", nullable = false)
  private ProducaoDia producaoDia;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "estoque_id", nullable = false)
  private Estoque estoque;

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

  public ProducaoDia getProducaoDia() {
    return producaoDia;
  }

  public void setProducaoDia(ProducaoDia producaoDia) {
    this.producaoDia = producaoDia;
  }

  public Estoque getEstoque() {
    return estoque;
  }

  public void setEstoque(Estoque estoque) {
    this.estoque = estoque;
  }
}
