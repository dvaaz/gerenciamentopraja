package gerenciamentorestaurante.projeto1.entitites;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name= "grupo")
public class Grupo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="gupo_id")
  private int id;
  @Column(name = "grupo_cor")
  private String cor;
  @Column(name = "grupo_tipo")
  private int tipo;
  @Column(name = "grupo_status")
  private int status;

  @OneToMany(mappedBy = "ingrediente")
  private Set<Ingrediente> ingredienteSet;

  @OneToMany(mappedBy = "ficha_tecnica")
  private Set<FichaTecnica> fichaTecnicaSet;
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public int getTipo() {
    return tipo;
  }

  public void setTipo(int tipo) {
    this.tipo = tipo;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
