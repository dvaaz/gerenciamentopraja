package gerenciamentorestaurante.projeto1.dto.response;

import jakarta.persistence.Column;

import java.util.Date;

public class ProducaoDiaDTOResponse {
    private int id;
    private Date data;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
