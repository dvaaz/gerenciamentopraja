package gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica;

public class IngredienteFichaTecnicaDTORequest {

    private int unidadeMedida;
    private int qtd;
    private int status;
    private Integer ingrediente;
    private Integer fichaTecnica;


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
