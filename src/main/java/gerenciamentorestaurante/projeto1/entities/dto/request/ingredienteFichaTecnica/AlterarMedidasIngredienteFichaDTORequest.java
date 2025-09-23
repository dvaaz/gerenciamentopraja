package gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica;

public class AlterarMedidasIngredienteFichaDTORequest {
  private Integer qtd;
  private Integer unidadeMedida;
//  private String preparo;

  public Integer getQtd() {
    return qtd;
  }

  public void setQtd(Integer qtd) {
    this.qtd = qtd;
  }

//    public String getPreparo() {
//        return preparo;
//    }
//
//    public void setPreparo(String preparo) {
//        this.preparo = preparo;
//    }

    public Integer getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(Integer unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }
}
