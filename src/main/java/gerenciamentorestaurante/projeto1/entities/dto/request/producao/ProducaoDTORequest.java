package gerenciamentorestaurante.projeto1.entities.dto.request.producao;

public class ProducaoDTORequest {
    private Integer qtd;
    private Integer status;

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
