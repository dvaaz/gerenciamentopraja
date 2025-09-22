package gerenciamentorestaurante.projeto1.entities.dto;

import java.util.List;

public class ChangeToAnotherGrupoInBatchDTO {
    private Integer idGrupo;
    private List<Integer> idDosItens;

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public List<Integer> getIdDosItens() {
        return idDosItens;
    }

    public void setIdDosItens(List<Integer> idDoItem) {
        this.idDosItens = idDoItem;
    }
}
