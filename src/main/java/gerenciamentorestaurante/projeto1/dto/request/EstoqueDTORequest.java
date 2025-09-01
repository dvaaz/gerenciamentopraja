package gerenciamentorestaurante.projeto1.dto.request;

import java.util.Date;

public class EstoqueDTORequest {
    private Date dia;
    private Date validade;
    private int quantidade;

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
