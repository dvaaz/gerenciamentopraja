package gerenciamentorestaurante.projeto1.entitites;


import gerenciamentorestaurante.projeto1.Enum.UnidadeMedidaEnum.UnidadeMedida;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingrediente_id")
    private int id;
    @Column(name="ingrediente_nome")
    private String nome;
    @Column(name ="ingrediente_descricao")
    private String descricao;
    @Column(name="ingrediente_unidade_medida")
    private UnidadeMedida unidade_medida;
    @OneToOne(mappedBy = "ingrediente_alerta")
    private IngredienteAlerta ingrediente
    


    

}
