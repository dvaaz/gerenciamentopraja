package gerenciamentorestaurante.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="ingrediente_ficha_tecnica")
public class IngredienteFichaTecnica {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ingrediente_ficha_tecnica_id")
  private Integer id;
  @Column(name= "ingrediente_ficha_tecnica_qtd")
  private int qtd;
  @Column(name="ingrediente_ficha_tecnica_unidade_medida")
  private int unidadeMedida;
  @Column(name="ingrediente_ficha_tecnica_status")
  private int status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ingrediente_id", nullable = false)
  private Ingrediente ingredienteId;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ficha_tecnica_id", nullable = false)
  private FichaTecnica fichaTecnicaId;

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

  public int getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(int unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
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

  public FichaTecnica getFichaTecnicaId() {
    return fichaTecnicaId;
  }

  public void setFichaTecnicaId(FichaTecnica fichaTecnicaId) {
    this.fichaTecnicaId = fichaTecnicaId;
  }
}
