package gerenciamentorestaurante.projeto1.entities.dto.response.ingredienteFichaTecnica;

public class AlterarMedidasIngredienteFichaDTOResponse {
  private Integer id;
//  private String preparo;
  private Integer qtd;
  private Integer unidadeMedida;

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
