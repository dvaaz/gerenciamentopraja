package gerenciamentorestaurante.projeto1.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "producao_dia")
public class ProducaoDia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "producao_dia_id")
  private Integer id;
  @Column(name = "producao_dia_data")
  private Date data;
  @Column(name="producao_dia_status")
  private Integer status;
  @OneToMany(mappedBy = "producaoDiaId")
  private Set<Producao> producaoDias;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public Set<Producao> getProducaoDias() {
    return producaoDias;
  }

  public void setProducaoDias(Set<Producao> producaoDias) {
    this.producaoDias = producaoDias;
  }
}
