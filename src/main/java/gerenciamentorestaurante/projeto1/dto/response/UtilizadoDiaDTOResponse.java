package gerenciamentorestaurante.projeto1.dto.response;

import jakarta.persistence.Column;

public class UtilizadoDiaDTOResponse {
    private int id;
    private int quantidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
