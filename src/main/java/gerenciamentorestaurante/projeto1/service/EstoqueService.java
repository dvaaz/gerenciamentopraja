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
    // Resolvido com uma query em order by
    public List<Ingrediente> listarIngredientesDisponiveisEmEstoque(Integer ingredienteId){
        List<Ingrediente> listaDeIngredientes = this.estoqueRepository.listarIngredientesEmEstoque(ingredienteId)
        if (listaDeIngredientes.isEmpty()){
          return null;
        }
            return listaDeIngredientes;
        }


    }


    @Transactional
    public void utilizarQuantidade(Integer id, EstoqueQtdDTORequest dtoRequest) {
      List<Estoque> ingredientesEmEstoque = this.estoqueRepository.listarIngredientesEmEstoquePorValidade(id);
      if (ingredientesEmEstoque.isEmpty()){
        throw new RuntimeException("Não há ingredientes disponiveis");
      }
      Integer qtdSolicitada = dtoRequest.getQtd();
      Integer qtdAtual = dtoRequest.getQtd();
      for (Estoque estoque : ingredientesEmEstoque) {
        if ((estoque.getQtd() - qtdSolicitada) >=0){


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
