package gerenciamentorestaurante.projeto1.entitites;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "producao_dia")
public class ProducaoDia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "producao_dia_id")
  private int id;
  @Column(name = "producao_dia_data")
  private Date data;

  @OneToMany(mappedBy = "producao_dia")
  private Set<DisponivelDiaDetalhe> disponiveisDiaDetalheSet;

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

  public Set<DisponivelDiaDetalhe> getDisponiveisDiaDetalheSet() {
    return disponiveisDiaDetalheSet;
  }

  public void setDisponiveisDiaDetalheSet(Set<DisponivelDiaDetalhe> disponiveisDiaDetalheSet) {
    this.disponiveisDiaDetalheSet = disponiveisDiaDetalheSet;
  }
}
