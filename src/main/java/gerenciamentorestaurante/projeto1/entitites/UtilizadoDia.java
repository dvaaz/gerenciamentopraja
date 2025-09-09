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
  @Column(name = "utilizado_dia_qtd")
  private int qtd;
  @Column(name="utilizado_dia_destino")
  private int destino;
  @Column(name="utilizado_dia_status")
  private int status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="ingrediente", nullable = false)
  private IngredienteFichaTecnica ingredienteFichaTecnica;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="producao_dia", nullable = false)
  private ProducaoDia producaoDia;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name="producao", nullable = false)
  private Producao producao;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public IngredienteFichaTecnica getIngredienteFichaTecnica() {
    return ingredienteFichaTecnica;
  }

  public void setIngredienteFichaTecnica(IngredienteFichaTecnica ingredienteFichaTecnica) {
    this.ingredienteFichaTecnica = ingredienteFichaTecnica;
  }

  public ProducaoDia getProducaoDia() {
    return producaoDia;
  }

  public void setProducaoDia(ProducaoDia producaoDia) {
    this.producaoDia = producaoDia;
  }

  public Producao getProducao() {
    return producao;
  }

  public void setProducao(Producao producao) {
    this.producao = producao;
  }
}
