package gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica;

public class IngredienteFichaTecnicaDTORequest {

    private Integer unidadeMedida;
    private Integer qtd;
//    private String preparo;
    private Integer ingrediente;
    private Integer fichaTecnica;
    private Integer status;

  public Integer getUnidadeMedida() {
    return unidadeMedida;
  }

  public void setUnidadeMedida(Integer unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
