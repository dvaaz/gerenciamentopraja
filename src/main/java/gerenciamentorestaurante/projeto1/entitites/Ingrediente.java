package gerenciamentorestaurante.projeto1.entitites;


import jakarta.persistence.*;

import java.util.Set;

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
    private int unidade_medida;

    @OneToMany(mappedBy = "ingrediente")
    private Set<IngredienteReceita> receitaIngredientes;

    @OneToMany(mappedBy = "ingrediente")
    private Set<Estoque> estoqueIngredientes;
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(int unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

     public Set<IngredienteReceita> getReceitaIngredientes() {
        return receitaIngredientes;
    }

    public void setReceitaIngredientes(Set<IngredienteReceita> receitaIngredientes) {
        this.receitaIngredientes = receitaIngredientes;
    }

    public Set<Estoque> getEstoqueIngredientes() {
        return estoqueIngredientes;
    }

    public void setEstoqueIngredientes(Set<Estoque> estoqueIngredientes) {
        this.estoqueIngredientes = estoqueIngredientes;
    }
}
