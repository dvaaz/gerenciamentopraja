package gerenciamentorestaurante.projeto1.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredienteFichaTecnicaDTORequest {
    private String nome;
    private String unidadeMedida;
    private int qtd;
    private int status;
    @JsonProperty("ingredienteId")
    private Integer ingrediente;
    @JsonProperty("fichaTecnicaId")
    private Integer fichaTecnica;

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

    public Integer getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(Integer fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }
}
