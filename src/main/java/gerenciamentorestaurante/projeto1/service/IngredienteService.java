package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoInBatchDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingrediente.IngredienteDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.ingrediente.IngredienteDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.exception.ElementNotFoundException;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Ingrediente novoIngrediente = new Ingrediente();
        novoIngrediente.setNome(dtoRequest.getNome());
        novoIngrediente.setDescricao(dtoRequest.getDescricao());
        novoIngrediente.setGrupo(grupo);
        novoIngrediente.setStatus(dtoRequest.getStatus());
        // Salva no banco de dados (persistence)
        Ingrediente ingredienteSave = ingredienteRepository.save(novoIngrediente);

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

            UpdateDescricaoResponse updated = new UpdateDescricaoResponse();
            updated.setId(tempResponse.getId());
            updated.setDescricao(tempResponse.getDescricao());

            return updated;
        } throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            updated.setIdGrupo(tempResponse.getGrupo().getId());

            return updated;
        } else return null;
    }

    public List<Ingrediente> listarIngredientesSelecionados(List<Integer> listaIngrediente) {
        return ingredienteRepository.listarIngredientesEmLista(listaIngrediente);
    }

    @Transactional
    public ChangeToAnotherGrupoInBatchDTOResponse alterarGrupoListaDeIngredientes(Integer idGrupo, List<Integer> listaIdIngredientes) {
        Grupo grupoExistente = grupoRepository.buscarGrupoDeIngredientesPorId(idGrupo);
           if  (grupoExistente == null) {
            throw new ElementNotFoundException("Grupo não encontrado" + idGrupo);
         }

          // buscar ingredientes para alterar o grupo
         List<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();
         listaIngredientes = this.listarIngredientesSelecionados(listaIdIngredientes);
//           if (listaIngredientes.isEmpty()) {
//               throw new ElementNotFoundException("Nenhum ingrediente encontrado");
//           }
//            Considerar se há a necessidade de não continuar caso todos os ingredientes incluidos nao sejam encontrados,
//             pois caso isso aconteça há um problema de lógica na busca de ingrediente
        if (listaIngredientes.size() != listaIdIngredientes.size()) {
            throw new ElementNotFoundException("Um ou mais ingredientes na lista fornecida não foram encontrados.");
        }
        //altera o grupo dos itens
            for (Ingrediente ingrediente : listaIngredientes) {
                ingrediente.setGrupo(grupoExistente);
            }
            List<Ingrediente> ingredientesSalvos = ingredienteRepository.saveAll(listaIngredientes);

// testando map para transformar os ids de ingrediente em lista
        List<Integer> listaIdDto = ingredientesSalvos.stream().map(Ingrediente::getId)
                .collect(Collectors.toList());
        // dto de resposta
        ChangeToAnotherGrupoInBatchDTOResponse dtoResponse = new ChangeToAnotherGrupoInBatchDTOResponse();
        dtoResponse.setIdGrupo(idGrupo);
        dtoResponse.setIdDosItens(listaIdDto);

        return dtoResponse;
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
        } else             throw new RuntimeException("Ingrediente não encontrado");
    }

    @Transactional
    public void apagarLogicoIngrediente(Integer ingredienteId){
      this.ingredienteRepository.apagarLogicoIngrediente(ingredienteId);
    }

    @Transactional
    public void deletarDefinitivoIngrediente(Integer ingredienteId){
    Ingrediente ingrediente = this.buscaFindById(ingredienteId);
    if (ingrediente != null && ingrediente.getStatus() <= -1) {
        this.ingredienteRepository.deleteById(ingredienteId);
    }
    }


}
