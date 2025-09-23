package gerenciamentorestaurante.projeto1.entities.dto.request.ingrediente;

public class IngredienteDTORequest {
    private String nome;
    private String descricao;
    private Integer grupoId;
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
