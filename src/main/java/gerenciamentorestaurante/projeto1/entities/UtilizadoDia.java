package gerenciamentorestaurante.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "utilizado_dia")
public class UtilizadoDia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "utilizado_dia_id")
  private Integer id;
  @Column(name = "utilizado_dia_qtd")
  private Integer qtd;
  @Column(name="utilizado_dia_destino")
  private Integer destino;
  @Column(name="utilizado_dia_status")
  private Integer status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="ingrediente_id", nullable = false)
  private Ingrediente ingredienteId;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="producao_dia_id", nullable = false)
  private ProducaoDia producaoDiaId;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="producao_id", nullable = false)
  private Producao producaoId;

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

  public Integer getDestino() {
    return destino;
  }

  public void setDestino(Integer destino) {
    this.destino = destino;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Ingrediente getIngredienteId() {
    return ingredienteId;
  }

  public void setIngredienteId(Ingrediente ingredienteId) {
    this.ingredienteId = ingredienteId;
  }

  public ProducaoDia getProducaoDiaId() {
    return producaoDiaId;
  }

  public void setProducaoDiaId(ProducaoDia producaoDiaId) {
    this.producaoDiaId = producaoDiaId;
  }

  public Producao getProducaoId() {
    return producaoId;
  }

  public void setProducaoId(Producao producaoId) {
    this.producaoId = producaoId;
  }
}
