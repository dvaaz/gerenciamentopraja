package gerenciamentorestaurante.projeto1.entities.dto.response.ingredienteFichaTecnica;

public class IngredienteFichaTecnicaDTOResponse {
    private Integer id;
  private Integer unidadeMedida;
  private Integer qtd;
//  private Integer observacao;
  private Integer status;
  private Integer ingrediente;
  private Integer fichaTecnica;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

//    public Integer getObservacao() {
//        return observacao;
//    }
//
//    public void setObservacao(Integer observacao) {
//        this.observacao = observacao;
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
