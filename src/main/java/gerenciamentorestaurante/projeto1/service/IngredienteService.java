package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteReceitaDTOResponse;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import gerenciamentorestaurante.projeto1.entitites.Ingrediente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteService {
    private final IngredienteRepository ingredienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    /**
     * Criar Ingredientes
     * @param ingredienteDTORequest
     * @return ingredienteDTOResponse
     */
    public IngredienteDTOResponse criarIngrediente(IngredienteDTORequest ingredienteDTORequest) {
        Ingrediente ingrediente = modelMapper.map(ingredienteDTORequest, Ingrediente.class);
        Ingrediente ingredienteSave = this.ingredienteRepository.save(ingrediente);
        IngredienteDTOResponse ingredienteDTOResponse = modelMapper.map(ingredienteSave, IngredienteDTOResponse.class);

        return ingredienteDTOResponse;
    }

    /**
     * Listar a todos os ingredientes
     */
    public List<IngredienteDTOResponse> listarIngredientes(){
        IngredienteDTOResponse ingredienteDTOResponse= modelMapper.map(this.ingredienteRepository.findAll(), IngredienteDTOResponse.class);
        return (List<IngredienteDTOResponse>) ingredienteDTOResponse;
    }

}
