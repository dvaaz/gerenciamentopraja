package gerenciamentorestaurante.projeto1.entities.dto.request;

import jakarta.persistence.Column;

public class AlterarMedidasIngredienteFichaDTORequest {
  private int qtd;
  private int unidadeMedida;

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
