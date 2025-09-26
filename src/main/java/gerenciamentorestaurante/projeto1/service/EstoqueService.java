package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.Estoque;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueQtdDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueQtdDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueDTOResponse;
import gerenciamentorestaurante.projeto1.enumerator.StatusEnum;
import gerenciamentorestaurante.projeto1.repository.EstoqueRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final IngredienteRepository ingredienteRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, IngredienteRepository ingredienteRepository) {
        this.estoqueRepository = estoqueRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional
    public EstoqueDTOResponse criarEstoque(EstoqueDTORequest dtoRequest) {
        Ingrediente ingrediente = ingredienteRepository.buscarIngredientePorId(dtoRequest.getIngredienteId());
        if (ingrediente == null) {
            throw new RuntimeException("Ingrediente não encontrado");
        }
        Estoque novoEstoque = new Estoque();
        novoEstoque.setDia(dtoRequest.getDia());
        novoEstoque.setValidade(dtoRequest.getValidade());
        novoEstoque.setQtd(dtoRequest.getQtd());
        novoEstoque.setStatus(dtoRequest.getIngredienteId());
        novoEstoque.setStatus(dtoRequest.getStatus());

        Estoque estoqueSave = estoqueRepository.save(novoEstoque);

        EstoqueDTOResponse dtoResponse = new EstoqueDTOResponse();
        dtoResponse.setId(estoqueSave.getId());
        dtoResponse.setDia(estoqueSave.getDia());
        dtoResponse.setValidade(estoqueSave.getValidade());
        dtoResponse.setQtd(estoqueSave.getQtd());
        dtoResponse.setIngredienteId(estoqueSave.getIngredienteId().getId());
        dtoResponse.setStatus(estoqueSave.getStatus());

        return dtoResponse;
    }

    public List<Estoque> listarEstoques() {
        return this.estoqueRepository.listarEstoques();
    }

    public Estoque buscarEstoquePorId(Integer id) {
        return this.estoqueRepository.buscarEstoquePorID(id);
    }


    @Transactional
    public EstoqueQtdDTOResponse quantidadeDisponivelEmUmEstoque(Integer id){
        Estoque estoque = estoqueRepository.buscarEstoquePorID(id);
        if (estoque != null) {
            EstoqueQtdDTOResponse dtoResponse = new EstoqueQtdDTOResponse();
            dtoResponse.setId(estoque.getId());
            dtoResponse.setQtd(estoque.getQtd());

            return dtoResponse;
        } throw new RuntimeException("Estoque não encontrado");
    }
    
    // Operação ideal = puxar uma lista de ingredientes, comparar sua data de validade, utilizar primeiro os que estejam mais proximos ao vencimento (after) 
    // e quando não houver quantiddade suficiente verificar o proximo da lista
    // a ordenação está sendo resolvida na chamada do banco pelo repository

    @Transactional
    public void utilizarQuantidade(Integer id, EstoqueQtdDTORequest dtoRequest) {
      List<Estoque> ingredientesEmEstoque = this.estoqueRepository.listarIngredientesEmEstoquePorValidade(id);
      if (ingredientesEmEstoque.isEmpty()){
        throw new RuntimeException("Não há ingredientes disponiveis");
      }
      // armazena a quantidade disponivel e verifica se é possivel obter no estoque

      Integer qtdNecessaria = dtoRequest.getQtd();
      Integer totalDisponivel = 0;
        for (Estoque estoque: ingredientesEmEstoque){
            if (totalDisponivel >= qtdNecessaria){
                break;
            }
            totalDisponivel += estoque.getQtd();
        }
      if (qtdNecessaria > totalDisponivel){
          throw new RuntimeException("Estoque insuficiente. Faltaram " + (qtdNecessaria - totalDisponivel) + " unidades.");
      }

      for (Estoque estoque: ingredientesEmEstoque){
            if(qtdNecessaria == 0 ){
                break;
            }

            Integer qtdARetirar;
            Integer qtdDisponivel = estoque.getQtd(); // armazena qtd disponivel

            if (qtdNecessaria >= qtdDisponivel){ // se houver menor quantidade em estoque (ou igual) que a solicitada
                qtdARetirar = qtdDisponivel; // retira qtd possivel
                estoque.setQtd(0);
                qtdNecessaria -= qtdARetirar;
            }
            if (qtdNecessaria < qtdDisponivel){ // se Houver mais quantidade em estoque que a solicitada
                qtdARetirar = qtdNecessaria;
                int novaQuantidade = qtdDisponivel - qtdARetirar;
                estoque.setQtd(novaQuantidade);
                qtdNecessaria = 0;
            }
      }



    }

    @Transactional
    public UpdateStatusResponse atualizarStatusEstoque(Integer id, EstoqueQtdDTOResponse dtoRequest) {
        Estoque estoque = this.estoqueRepository.buscarEstoquePorID(id);
        if (estoque == null) {
            Estoque novoStatusEstoque = estoqueRepository.save(estoque);

            UpdateStatusResponse dtoResponse = new UpdateStatusResponse();
            dtoResponse.setId(novoStatusEstoque.getId());
            dtoResponse.setStatus(novoStatusEstoque.getStatus());
            return dtoResponse;
        }
        throw new RuntimeException("Estoque não encontrado");
    }

    @Transactional
    public void apagarLogicoEstoque(Integer id) {
        this.estoqueRepository.apagarLogicoEstoque(id);
    }
}
