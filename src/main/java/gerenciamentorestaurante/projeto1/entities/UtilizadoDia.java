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
  private int qtd;
  @Column(name="utilizado_dia_destino")
  private int destino;
  @Column(name="utilizado_dia_status")
  private int status;
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

  public int getQtd() {
    return qtd;
  }

  public void setQtd(int qtd) {
    this.qtd = qtd;
  }

  public int getDestino() {
    return destino;
  }

  public void setDestino(int destino) {
    this.destino = destino;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
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
