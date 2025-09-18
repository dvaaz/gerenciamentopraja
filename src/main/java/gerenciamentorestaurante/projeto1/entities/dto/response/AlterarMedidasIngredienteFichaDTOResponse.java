package gerenciamentorestaurante.projeto1.entities.dto.response;

public class AlterarMedidasIngredienteFichaDTOResponse {
  private Integer id;
  private int qtd;
  private int unidadeMedida;

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
}
