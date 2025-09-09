package gerenciamentorestaurante.projeto1.entitites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "producao")
public class Producao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "producao_id")
  private int id;
  @Column(name = "producao_quantidade")
  private int quantidade;
  @Column(name = "producao_status")
  private int status;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "producao_dia_id", nullable = false)
  private ProducaoDia producaoDia;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "estoque_id", nullable = false)
  private Estoque estoque;


}
