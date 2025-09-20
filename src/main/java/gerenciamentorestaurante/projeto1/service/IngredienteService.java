package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.dto.request.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.ChangeToAnotherGrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    private GrupoService grupoService;

    public IngredienteService(IngredienteRepository ingredienteRepository, GrupoRepository grupoRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.grupoRepository = grupoRepository;
    }

    /**
     * Criar Ingredientes
     * @return ingredienteDTOResponse
     */
    @Transactional
    public IngredienteDTOResponse criarIngrediente(IngredienteDTORequest dtoRequest) {
        // Verifica a existencia do um grupo
        Grupo grupo = grupoRepository.buscarGrupoDeIngredientesPorId(dtoRequest.getGrupoId());
        // Se não encontrar um grupo
        if (grupo == null) {
            grupo = grupoRepository.buscarGrupoPadrao();
            // se não houver um grupo do tipo padrão criado, nós criaremos um
            if (grupo == null) {
                grupoService.criarGrupoDefault();
                grupo = grupoRepository.buscarGrupoPadrao();
            }
        }
        // Mapeia os dados obtidos para  acriação de ingrediente
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNome(dtoRequest.getNome());
        ingrediente.setDescricao(dtoRequest.getDescricao());
        ingrediente.setGrupo(grupo);
        ingrediente.setStatus(dtoRequest.getStatus());
        // Salva no banco de dados (persistence)
        Ingrediente ingredienteSave = ingredienteRepository.save(ingrediente);

        IngredienteDTOResponse dtoResponse= new IngredienteDTOResponse();
        dtoResponse.setId(ingredienteSave.getId());
        dtoResponse.setNome(ingredienteSave.getNome());
        dtoResponse.setDescricao(ingredienteSave.getDescricao());
        dtoResponse.setGrupoId(ingredienteSave.getGrupo().getId());
        dtoResponse.setStatus(ingredienteSave.getStatus());

        return dtoResponse;
    }

    public Ingrediente buscaFindById(Integer ingredienteID) {
        return ingredienteRepository.findById(ingredienteID).orElse(null);
    }

    public List<Ingrediente> listarIngredientes(){
        return this.ingredienteRepository.listarIngredientes();
    }

    public Ingrediente listarIngredientePorId(Integer ingredienteId){
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

            UpdateDescricaoResponse updated = new UpdateDescricaoResponse();
            updated.setId(tempResponse.getId());
            updated.setDescricao(tempResponse.getDescricao());

            return updated;
        } else return null;
    }

    @Transactional
    public ChangeToAnotherGrupoDTOResponse alterarGrupoIngrediente(Integer ingredienteId, Integer novoGrupo) {
        Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
        Grupo alteraGrupo = this.grupoRepository.buscarGrupoDeIngredientesPorId(novoGrupo);
        if  (ingrediente != null &&  alteraGrupo != null){
            ingrediente.setGrupo(alteraGrupo);
            Ingrediente tempResponse = ingredienteRepository.save(ingrediente);

            ChangeToAnotherGrupoDTOResponse updated = new ChangeToAnotherGrupoDTOResponse();
            updated.setId(tempResponse.getId());
            updated.setGrupo(tempResponse.getGrupo().getId());

            return updated;
        } else return null;
    }

    @Transactional
    public UpdateStatusResponse atualizarStatusIngrediente(Integer ingredienteId, UpdateStatusRequest updateStatusRequest){
        Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(ingredienteId);
        if (ingrediente != null){
            ingrediente.setStatus(updateStatusRequest.getStatus());
            Ingrediente tempResponse = ingredienteRepository.save(ingrediente);

            UpdateStatusResponse updated = new UpdateStatusResponse();
            updated.setId(tempResponse.getId());
            updated.setStatus(tempResponse.getStatus());

            return updated;
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
