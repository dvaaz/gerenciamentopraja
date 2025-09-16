package gerenciamentorestaurante.projeto1.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ingrediente_id")
    private Integer id;
    @Column(name="ingrediente_nome")
    private String nome;
    @Column(name ="ingrediente_descricao")
    private String descricao;
    @Column(name="ingrediente_status")
    private int status;
    @OneToMany(mappedBy = "ingredienteId")
    private Set<Estoque> ingredientesNoEstoque;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;
    @OneToMany(mappedBy = "ingredienteId")
    private Set<IngredienteFichaTecnica> ingredienteNaFichaTecnica;
    @OneToMany(mappedBy = "ingredienteId")
    private Set<UtilizadoDia> ingredienteUtilizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Estoque> getIngredientesNoEstoque() {
        return ingredientesNoEstoque;
    }

    public void setIngredientesNoEstoque(Set<Estoque> ingredientesNoEstoque) {
        this.ingredientesNoEstoque = ingredientesNoEstoque;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupoId) {
        this.grupo = grupoId;
    }

    public Set<IngredienteFichaTecnica> getIngredienteNaFichaTecnica() {
        return ingredienteNaFichaTecnica;
    }

    public void setIngredienteNaFichaTecnica(Set<IngredienteFichaTecnica> ingredienteNaFichaTecnica) {
        this.ingredienteNaFichaTecnica = ingredienteNaFichaTecnica;
    }

    public Set<UtilizadoDia> getIngredienteUtilizado() {
        return ingredienteUtilizado;
    }

    public void setIngredienteUtilizado(Set<UtilizadoDia> ingredienteUtilizado) {
        this.ingredienteUtilizado = ingredienteUtilizado;
    }
}
