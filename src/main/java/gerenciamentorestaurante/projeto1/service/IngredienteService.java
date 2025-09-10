package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateGrupoRequest;
import gerenciamentorestaurante.projeto1.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateGrupoResponse;
import gerenciamentorestaurante.projeto1.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entitites.Grupo;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
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
    private final GrupoRepository grupoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public IngredienteService(IngredienteRepository ingredienteRepository, GrupoRepository grupoRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.grupoRepository = grupoRepository;
    }

    /**
     * Criar Ingredientes
     * @return ingredienteDTOResponse
     */
    public IngredienteDTOResponse criarIngrediente(IngredienteDTORequest ingredienteDTORequest) {
        Ingrediente ingrediente = modelMapper.map(ingredienteDTORequest, Ingrediente.class);
        Grupo grupo = grupoRepository.buscarGrupoDeIngredientePorId(ingrediente.getGrupo().getId());
        if (grupo == null) {
            // grupo default para todos ingredientes
            Grupo grupoDefault = this.grupoRepository.buscarGrupoPorID(1);
            ingrediente.setGrupo(grupoDefault);
        }
        Ingrediente ingredienteSave = this.ingredienteRepository.save(ingrediente);
        IngredienteDTOResponse ingredienteDTOResponse = modelMapper.map(ingredienteSave, IngredienteDTOResponse.class);

        return ingredienteDTOResponse;
    }

    @Transactional
    public Ingrediente buscaFindById(Integer ingredienteID) {
        return ingredienteRepository.findById(ingredienteID).orElse(null);
    }


    public List<IngredienteDTOResponse> listarIngredientes(){
      List<Ingrediente> ingredientes = this.ingredienteRepository.findAll();
      List<IngredienteDTOResponse> listaDTO = ingredientes.stream()
          .map(ingrediente -> modelMapper.map(ingrediente, IngredienteDTOResponse.class))
          .toList();

        return null;
    }

    public Ingrediente buscarIngredientePorId(Integer ingredienteId){
      Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
      if  (ingrediente != null){
        return this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
      } else return null;
    }

    @Transactional
    public UpdateDescricaoResponse atualizarDescricaoIngrediente(Integer ingredienteId, UpdateDescricaoRequest updateDescricaoRequest) {
        Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
        if  (ingrediente != null){
            ingrediente.setDescricao(updateDescricaoRequest.getDescricao());
            Ingrediente tempResponse = ingredienteRepository.save(ingrediente);
            return modelMapper.map(tempResponse, UpdateDescricaoResponse.class);
        } else return null;
    }

    @Transactional
    public UpdateGrupoResponse alterarGrupoIngrediente(Integer ingredienteId, UpdateGrupoRequest updateGrupoRequest) {
        Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
        Grupo grupo = this.grupoRepository.buscarGrupoDeIngredientePorId(updateGrupoRequest.getGrupo());
        if  (ingrediente != null &&  grupo != null){
            Grupo grupoDefault = grupoRepository.buscarGrupoPorID(1);
            ingrediente.setGrupo(grupoDefault);
            Ingrediente tempResponse = ingredienteRepository.save(ingrediente);
            return modelMapper.map(tempResponse, UpdateGrupoResponse.class);
        } else return null;
    }

    @Transactional
    public UpdateStatusResponse atualizarStatusIngrediente(Integer ingredienteId, UpdateStatusRequest updateStatusRequest){
        Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null){
            ingrediente.setStatus(updateStatusRequest.getStatus());
            Ingrediente tempResponse = ingredienteRepository.save(ingrediente);
            return modelMapper.map(tempResponse, UpdateStatusResponse.class);
        } else return null;
    }

    @Transactional
    public void apagarLogicoIngrediente(Integer ingredienteId){
      this.ingredienteRepository.apagarLogicoIngrediente(ingredienteId);
    }

    @Transactional
    public void deletarDefinitivoIngrediente(Integer ingredienteId){
    Ingrediente ingrediente = this.buscaFindById(ingredienteId);
    if (ingrediente != null && ingrediente.getStatus() == -1) {
        this.ingredienteRepository.deleteById(ingredienteId);
    }
    }


}
