package gerenciamentorestaurante.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producao")
public class Producao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "producao_id")
  private Integer id;
  @Column(name = "producao_qtd")
  private Integer qtd;
  @Column(name = "producao_status")
  private Integer status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "producao_dia_id", nullable = false)
  private ProducaoDia producaoDiaId;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "estoque_id", nullable = false)
  private Estoque estoqueId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getQtd() {
    return qtd;
  }

  public void setQtd(Integer qtd) {
    this.qtd = qtd;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public ProducaoDia getProducaoDiaId() {
    return producaoDiaId;
  }

  public void setProducaoDiaId(ProducaoDia producaoDiaId) {
    this.producaoDiaId = producaoDiaId;
  }

  public Estoque getEstoqueId() {
    return estoqueId;
  }

  public void setEstoqueId(Estoque estoqueId) {
    this.estoqueId = estoqueId;
  }
}
