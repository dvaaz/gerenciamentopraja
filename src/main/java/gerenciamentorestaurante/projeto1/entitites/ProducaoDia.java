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
  @Column(name="producao_dia_status")
  private int status;
  @OneToMany(mappedBy = "producao_dia")
  private Set<Producao> disponiveisDiaDetalheSet;

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

  public Set<Producao> getDisponiveisDiaDetalheSet() {
    return disponiveisDiaDetalheSet;
  }

  public void setDisponiveisDiaDetalheSet(Set<Producao> disponiveisDiaDetalheSet) {
    this.disponiveisDiaDetalheSet = disponiveisDiaDetalheSet;
  }
}
