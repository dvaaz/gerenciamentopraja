package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.Estoque;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.dto.EstoqueAlterarQtdDTO;
import gerenciamentorestaurante.projeto1.entities.dto.request.estoque.EstoqueDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.estoque.EstoqueDTOResponse;
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
        dtoResponse.setIngredienteId(estoqueSave.getIngrediente().getId());
        dtoResponse.setStatus(estoqueSave.getStatus());

        return dtoResponse;
    }

    public List<Estoque> listarEstoques() {
        return this.estoqueRepository.listarEstoques();
    }

    public Estoque buscarEstoquePorId(Integer id) {
        return this.estoqueRepository.buscararEstoquePorID(id);
    }

    @Transactional
    public EstoqueAlterarQtdDTO utilizarQuantidade(EstoqueAlterarQtdDTO dtoRequest) {
        Estoque estoque = estoqueRepository.buscararEstoquePorID(dtoRequest.getId());
        if (estoque == null) {
            throw new RuntimeException("Estoque não encontrado");
        }

        Integer novaQuantidade = estoque.getQtd() - dtoRequest.getQtd();

        if (novaQuantidade < estoque.getQtd() && novaQuantidade >= 0)  {
            estoque.setQtd(novaQuantidade);

            Estoque novaQtdEstoque = estoqueRepository.save(estoque);

            EstoqueAlterarQtdDTO dtoResponse = new EstoqueAlterarQtdDTO();
            dtoResponse.setId(novaQtdEstoque.getId());
            dtoResponse.setQtd(novaQtdEstoque.getQtd());

            return dtoResponse;
        }
        throw new RuntimeException("Estoque não possui quantidade suficiente de insumos");
    }

    @Transactional
    public UpdateStatusResponse atualizarStatusEstoque(Integer id, EstoqueAlterarQtdDTO dtoRequest) {
        Estoque estoque = this.estoqueRepository.buscararEstoquePorID(id);
        if (estoque == null) {
            estoque.setStatus(dtoRequest.getStatus());
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
