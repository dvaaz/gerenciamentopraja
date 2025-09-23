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
  private Integer qtd;
  @Column(name="estoque_status")
  private Integer status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ingrediente_id", nullable = false)
  private Ingrediente ingrediente;

  @OneToMany(mappedBy = "estoqueId")
  private Set<Producao> producao;

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

  public Integer getQtd() {
    return qtd;
  }

  public void setQtd(Integer qtd) {
    this.qtd = qtd;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Set<Producao> getProducao() {
    return producao;
  }

  public void setProducao(Set<Producao> producao) {
    this.producao = producao;
  }
}
