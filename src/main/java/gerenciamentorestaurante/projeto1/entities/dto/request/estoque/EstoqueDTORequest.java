package gerenciamentorestaurante.projeto1.entities.dto.request.estoque;

//import gerenciamentorestaurante.projeto1.validation.anotation.OrdemDasDatas;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;

// Validator Odem das datas não está funcionando

import java.util.Date;

public class EstoqueDTORequest {
   @NotEmpty
//   @OrdemDasDatas(primeiraData = "dia", segundaData = "validade", message = "Entrada não pode ser anterior à fabricação")
    private Date dia;
    @NotEmpty
    @Future
    private Date validade;
    private Integer qtd;
    private Integer ingredienteId;
    private Integer status;

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

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Integer getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Integer ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
