package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteReceitaDTOResponse;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import gerenciamentorestaurante.projeto1.entitites.Ingrediente;
import jakarta.transaction.Transactional;
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
    public IngredienteDTOResponse registrarIngrediente(IngredienteDTORequest ingredienteDTORequest) {
        Ingrediente ingrediente = modelMapper.map(ingredienteDTORequest, Ingrediente.class);
        Ingrediente ingredienteSave = this.ingredienteRepository.save(ingrediente);
        IngredienteDTOResponse ingredienteDTOResponse = modelMapper.map(ingredienteSave, IngredienteDTOResponse.class);

        return ingredienteDTOResponse;
    }
    //Listar a todos os ingredientes
    @Transactional
    public List<IngredienteDTOResponse> listarIngredientes(){
      List<Ingrediente> ingredientes = this.ingredienteRepository.findAll();
      List<IngredienteDTOResponse> listaDTO = ingredientes.stream()
          .map(ingrediente -> modelMapper.map(ingrediente, IngredienteDTOResponse.class))
          .toList();

        return null;
    }
    @Transactional
    public IngredienteDTOResponse buscarIngredientePorId(Integer ingredienteId){
      Ingrediente ingrediente = this.ingredienteRepository.obterIngredientePorID(ingredienteId);
      if  (ingrediente != null){
        return this.ingredienteRepository.obterIngredientePorID(ingredienteId)
      } else return null;

    }

    @Transactional
    public void apagarIngrediente(Integer ingredienteId){
      this.ingredienteRepository.apagarLogicoIngrediente(ingredienteId);
    }




}
