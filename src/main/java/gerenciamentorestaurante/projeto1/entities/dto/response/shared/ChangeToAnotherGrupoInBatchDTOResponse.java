package gerenciamentorestaurante.projeto1.entities.dto.response.shared;

import java.util.List;

public class ChangeToAnotherGrupoInBatchDTOResponse {
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
