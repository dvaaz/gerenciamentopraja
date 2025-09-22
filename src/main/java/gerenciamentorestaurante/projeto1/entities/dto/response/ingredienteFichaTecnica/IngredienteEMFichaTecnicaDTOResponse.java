package gerenciamentorestaurante.projeto1.entities.dto.response.ingredienteFichaTecnica;

public class IngredienteEMFichaTecnicaDTOResponse {
    private Integer id;
    private int unidadeMedida;
    private int qtd;
    private Integer idIngrediente;
    private String nomeIngrediente;

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

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }
}
