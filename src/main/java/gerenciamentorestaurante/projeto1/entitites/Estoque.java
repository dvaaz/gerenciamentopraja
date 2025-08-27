package gerenciamentorestaurante.projeto1.entitites;

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
  private int quantidade;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "ingrediente_id", nullable = false)
  private Ingrediente ingrediente;

  @OneToMany(mappedBy = "estoque")
  private Set<DisponivelDiaDetalhe> disponiveisDiaDetalheSet;

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

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  public Ingrediente getIngrediente() {
    return ingrediente;
  }

  public void setIngrediente(Ingrediente ingrediente) {
    this.ingrediente = ingrediente;
  }

  public Set<DisponivelDiaDetalhe> getDisponiveisDiaDetalheSet() {
    return disponiveisDiaDetalheSet;
  }

  public void setDisponiveisDiaDetalheSet(Set<DisponivelDiaDetalhe> disponiveisDiaDetalheSet) {
    this.disponiveisDiaDetalheSet = disponiveisDiaDetalheSet;
  }
}
