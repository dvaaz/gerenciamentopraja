package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.Estoque;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueQtdDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueQtdDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueDTOResponse;
import gerenciamentorestaurante.projeto1.enumerator.GrupoEnum;
import gerenciamentorestaurante.projeto1.enumerator.StatusEnum;
import gerenciamentorestaurante.projeto1.repository.EstoqueRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    @Transactional
    public List<Estoque> buscarIngredientesEmEstoque(Integer ingredienteId){
        List<Estoque> listaDeIngredientes = estoqueRepository.listarIngredientesEmEstoque(ingredienteId);
        if (!listaDeIngredientes.isEmpty()){
            return listaDeIngredientes;
        } return null;
    }

    @Transactional
    public EstoqueQtdDTOResponse utilizarQuantidade(Integer id, EstoqueQtdDTOResponse dtoRequest) {
        Estoque estoque = estoqueRepository.buscarEstoquePorID(id);
        if (estoque == null) {
            throw new RuntimeException("Estoque não encontrado");
        }

        Integer novaQuantidade = estoque.getQtd() - dtoRequest.getQtd();

        if (novaQuantidade <= estoque.getQtd() && novaQuantidade >= 0)  {
            estoque.setQtd(novaQuantidade);
            if (novaQuantidade == 0 ){
                // altera o status do estoque para não utilizado
                estoque.setStatus(StatusEnum.INATIVO.getStatus());
            }

            Estoque novaQtdEstoque = estoqueRepository.save(estoque);

            EstoqueQtdDTOResponse dtoResponse = new EstoqueQtdDTOResponse();
            dtoResponse.setId(novaQtdEstoque.getId());
            dtoResponse.setQtd(novaQtdEstoque.getQtd());

            return dtoResponse;
        }
        if (novaQuantidade > estoque.getQtd() && novaQuantidade >= 0)
        {

            List<Estoque> ingredientesDisponiveisEmEstoque = estoqueRepository.listarIngredientesEmEstoque(estoque.getIngredienteId().getId());

        }
        throw new RuntimeException("Estoque não possui quantidade suficiente de insumos");
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
