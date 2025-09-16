package gerenciamentorestaurante.projeto1.entities.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredienteDTORequest {
    private String nome;
    private String descricao;
    @JsonProperty("grupoId")
    private Integer grupoId;
    private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Integer getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Integer grupoId) {
        this.grupoId = grupoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
