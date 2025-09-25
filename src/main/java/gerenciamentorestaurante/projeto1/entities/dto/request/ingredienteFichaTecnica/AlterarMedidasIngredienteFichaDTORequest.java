package gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica;

public class AlterarMedidasIngredienteFichaDTORequest {
  private Integer qtd;
  private Integer unidadeMedida;
//  private String observacao;

  public Integer getQtd() {
    return qtd;
  }

  public void setQtd(Integer qtd) {
    this.qtd = qtd;
  }

//    public String getObservacao() {
//        return observacao;
//    }
//
//    public void setObservacao(String observacao) {
//        this.observacao = observacao;
//    }

    public Integer getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(Integer unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }
}
