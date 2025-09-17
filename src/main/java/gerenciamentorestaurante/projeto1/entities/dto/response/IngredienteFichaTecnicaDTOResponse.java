package gerenciamentorestaurante.projeto1.entities.dto.response;

public class IngredienteFichaTecnicaDTOResponse {
    private Integer id;
    private String nome;
    private String unidadeMedida;
    private int qtd;
    private int status;

    private Integer ingrediente;
    private Integer fichatecnica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
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

    public Integer getFichatecnica() {
        return fichatecnica;
    }

    public void setFichatecnica(Integer fichatecnica) {
        this.fichatecnica = fichatecnica;
    }
}
