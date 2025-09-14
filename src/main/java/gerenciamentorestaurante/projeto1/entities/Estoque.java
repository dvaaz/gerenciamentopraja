package gerenciamentorestaurante.projeto1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "estoque")
public class Estoque {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "estoque_id")
  private int id;
  @Column(name = "estoque_dia")
  private Date dia;
  @Column(name = "estoque_validade")
  private Date validade;
  @Column(name = "estoque_qtd")
  private int qtd;
  @Column(name="estoque_status")
  private int status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ingrediente_id", nullable = false)
  private Ingrediente ingrediente;

  @OneToMany(mappedBy = "estoque")
  private Set<Producao> disponiveisDiaDetalheSet;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public int getQtd() {
    return qtd;
  }

  public void setQtd(int qtd) {
    this.qtd = qtd;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Set<Producao> getDisponiveisDiaDetalheSet() {
    return disponiveisDiaDetalheSet;
  }

  public void setDisponiveisDiaDetalheSet(Set<Producao> disponiveisDiaDetalheSet) {
    this.disponiveisDiaDetalheSet = disponiveisDiaDetalheSet;
  }
}
