package gerenciamentorestaurante.projeto1.entities.dto.request.producaoDia;

import java.util.Date;

public class ProducaoDiaDTORequest {
    private Date data;
    private Integer status;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
