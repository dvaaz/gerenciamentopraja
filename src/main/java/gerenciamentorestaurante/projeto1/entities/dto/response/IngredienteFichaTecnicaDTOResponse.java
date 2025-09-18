package gerenciamentorestaurante.projeto1.entities.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredienteFichaTecnicaDTOResponse {
    private Integer id;
  private int unidadeMedida;
  private int qtd;
  private int status;
  @JsonProperty("ingredienteId")
  private Integer ingrediente;
  @JsonProperty("fichaTecnicaId")
  private Integer fichaTecnica;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public int getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(int unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }

  public int getQtd() {
    return qtd;
  }

  public void setQtd(int qtd) {
    this.qtd = qtd;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Integer getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Integer ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Integer getFichaTecnica() {
    return fichaTecnica;
  }

  public void setFichaTecnica(Integer fichaTecnica) {
    this.fichaTecnica = fichaTecnica;
  }
}
