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
  private Integer id;
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
  private Ingrediente ingredienteId;

  @OneToMany(mappedBy = "estoqueId")
  private Set<Producao> disponiveisDiaDetalhe;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Ingrediente getIngredienteId() {
    return ingredienteId;
  }

  public void setIngredienteId(Ingrediente ingredienteId) {
    this.ingredienteId = ingredienteId;
  }

  public Set<Producao> getDisponiveisDiaDetalhe() {
    return disponiveisDiaDetalhe;
  }

  public void setDisponiveisDiaDetalhe(Set<Producao> disponiveisDiaDetalhe) {
    this.disponiveisDiaDetalhe = disponiveisDiaDetalhe;
  }
}
