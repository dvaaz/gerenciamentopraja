package gerenciamentorestaurante.projeto1.dto.request;

import java.util.Date;

public class ProducaoDiaDTORequest {
    private Date data;
    private int status;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
