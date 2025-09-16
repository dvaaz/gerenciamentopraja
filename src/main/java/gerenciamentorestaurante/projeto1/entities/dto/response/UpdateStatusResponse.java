package gerenciamentorestaurante.projeto1.entities.dto.response;

public class UpdateStatusResponse {
    private Integer id;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
