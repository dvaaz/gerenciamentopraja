package gerenciamentorestaurante.projeto1.dto.request;

public class IngredienteDTORequest {
    private String nome;
    private String descricao;
    private int unidade_medida;

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

    public int getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(int unidade_medida) {
        this.unidade_medida = unidade_medida;
    }
}
