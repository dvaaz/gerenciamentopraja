package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.fichaTecnica.FichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.fichaTecnica.FichaTecnicaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.ChangeToAnotherGrupoInBatchDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.exception.ElementNotFoundException;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import gerenciamentorestaurante.projeto1.repository.FichaTecnicaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FichaTecnicaService {
  private final FichaTecnicaRepository fichaTecnicaRepository;
  private final GrupoRepository grupoRepository;

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private GrupoService grupoService;

  public FichaTecnicaService(FichaTecnicaRepository fichaTecnicaRepository, GrupoRepository grupoRepository) {
    this.fichaTecnicaRepository = fichaTecnicaRepository;
    this.grupoRepository = grupoRepository;
  }

  /**
   * Criar FichaTecnicas
   * @return fichaTecnicaDTOResponse
   */
  @Transactional
  public FichaTecnicaDTOResponse criarFichaTecnica(FichaTecnicaDTORequest dtoRequest) {
    // Verifica a existencia do um grupo
    Grupo grupo = grupoRepository.buscarGrupoDeFichasTecnicasPorId(dtoRequest.getGrupoId());
    // Se não encontrar um grupo
    if (grupo == null) {
      grupo = grupoRepository.buscarGrupoPadrao();
      // se não houver um grupo do tipo padrão criado, nós criaremos um
      if (grupo == null) {
        grupoService.criarGrupoDefault();
        grupo = grupoRepository.buscarGrupoPadrao();
      }
    }
    // Mapeia os dados obtidos para  acriação de fichaTecnica
    FichaTecnica fichaTecnica = new FichaTecnica();
    fichaTecnica.setNome(dtoRequest.getNome());
    fichaTecnica.setDescricao(dtoRequest.getDescricao());
    fichaTecnica.setGrupo(grupo);
    fichaTecnica.setStatus(dtoRequest.getStatus());
    // Salva no banco de dados (persistence)
    FichaTecnica fichaTecnicaSave = fichaTecnicaRepository.save(fichaTecnica);

    FichaTecnicaDTOResponse dtoResponse= new FichaTecnicaDTOResponse();
    dtoResponse.setId(fichaTecnicaSave.getId());
    dtoResponse.setNome(fichaTecnicaSave.getNome());
    dtoResponse.setDescricao(fichaTecnicaSave.getDescricao());
    dtoResponse.setGrupoId(fichaTecnicaSave.getGrupo().getId());
    dtoResponse.setStatus(fichaTecnicaSave.getStatus());

    return dtoResponse;
  }

  public FichaTecnica buscaFindById(Integer fichaTecnicaID) {
    return fichaTecnicaRepository.findById(fichaTecnicaID).orElse(null);
  }

  public List<FichaTecnica> listarFichaTecnicas(){
    return this.fichaTecnicaRepository.listarFichaTecnicas();
  }

  public FichaTecnica listarFichaTecnicaPorId(Integer fichaTecnicaId){
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(fichaTecnicaId);
    if  (fichaTecnica != null){
      return this.fichaTecnicaRepository.buscarFichaTecnicaPorID(fichaTecnicaId);
    } else return null;
  }

  @Transactional
  public UpdateDescricaoResponse atualizarDescricaoFichaTecnica(Integer fichaTecnicaId, UpdateDescricaoRequest updateDescricaoRequest) {
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(fichaTecnicaId);
    if  (fichaTecnica != null){
      fichaTecnica.setDescricao(updateDescricaoRequest.getDescricao());
      FichaTecnica tempResponse = fichaTecnicaRepository.save(fichaTecnica);

      UpdateDescricaoResponse updated = new UpdateDescricaoResponse();
      updated.setId(tempResponse.getId());
      updated.setDescricao(tempResponse.getDescricao());

      return updated;
    } else return null;
  }

  @Transactional
  public ChangeToAnotherGrupoDTOResponse alterarGrupoFichaTecnica(Integer fichaTecnicaId, Integer novoGrupo) {
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(fichaTecnicaId);
    Grupo alteraGrupo = this.grupoRepository.buscarGrupoDeFichasTecnicasPorId(novoGrupo);
    if  (fichaTecnica != null &&  alteraGrupo != null){
      fichaTecnica.setGrupo(alteraGrupo);
      FichaTecnica tempResponse = fichaTecnicaRepository.save(fichaTecnica);

      ChangeToAnotherGrupoDTOResponse updated = new ChangeToAnotherGrupoDTOResponse();
      updated.setId(tempResponse.getId());
      updated.setIdGrupo(tempResponse.getGrupo().getId());

      return updated;
    } else return null;
  }

  public List<FichaTecnica> listarFichasTecnicasEmLista(List<Integer> listaIngrediente) {
    return fichaTecnicaRepository.listarFichasTecnicasEmLista(listaIngrediente);
  }

  @Transactional
  public ChangeToAnotherGrupoInBatchDTOResponse alterarGrupoListaDeFichaTecnicas(Integer idGrupo, List<Integer> listaIdFichaTecnicas) {
    Grupo grupoExistente = grupoRepository.buscarGrupoDeFichasTecnicasPorId(idGrupo);
    if  (grupoExistente == null) {
      throw new ElementNotFoundException("Grupo não encontrado" + idGrupo);
    }

    // buscar fichatecnicas para alterar o grupo
    List<FichaTecnica> listaFichaTecnicas = new ArrayList<FichaTecnica>();
    listaFichaTecnicas = this.listarFichasTecnicasEmLista(listaIdFichaTecnicas);
//           if (listaFichaTecnicas.isEmpty()) {
//               throw new ElementNotFoundException("Nenhum fichatecnica encontrado");
//           }
//            Considerar se há a necessidade de não continuar caso todos os fichatecnicas incluidos nao sejam encontrados,
//             pois caso isso aconteça há um problema de lógica na busca de fichatecnica
    if (listaFichaTecnicas.size() != listaIdFichaTecnicas.size()) {
      throw new ElementNotFoundException("Um ou mais fichatecnicas na lista fornecida não foram encontrados.");
    }
    //altera o grupo dos itens
    for (FichaTecnica fichatecnica : listaFichaTecnicas) {
      fichatecnica.setGrupo(grupoExistente);
    }
    List<FichaTecnica> fichatecnicasSalvos = fichaTecnicaRepository.saveAll(listaFichaTecnicas);

// testando map para transformar os ids de fichatecnica em lista
    List<Integer> listaIdDto = fichatecnicasSalvos.stream().map(FichaTecnica::getId)
        .collect(Collectors.toList());
    // dto de resposta
    ChangeToAnotherGrupoInBatchDTOResponse dtoResponse = new ChangeToAnotherGrupoInBatchDTOResponse();
    dtoResponse.setIdGrupo(idGrupo);
    dtoResponse.setIdDosItens(listaIdDto);

    return dtoResponse;
  }

  @Transactional
  public UpdateStatusResponse atualizarStatusFichaTecnica(Integer fichaTecnicaId, UpdateStatusRequest updateStatusRequest){
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(fichaTecnicaId);
    if (fichaTecnica != null){
      fichaTecnica.setStatus(updateStatusRequest.getStatus());
      FichaTecnica tempResponse = fichaTecnicaRepository.save(fichaTecnica);

      UpdateStatusResponse updated = new UpdateStatusResponse();
      updated.setId(tempResponse.getId());
      updated.setStatus(tempResponse.getStatus());

      return updated;
    } else return null;
  }

  @Transactional
  public void apagarLogicoFichaTecnica(Integer fichaTecnicaId){
    this.fichaTecnicaRepository.apagarLogicoFichaTecnica(fichaTecnicaId);
  }

  @Transactional
  public void deletarDefinitivoFichaTecnica(Integer fichaTecnicaId){
    FichaTecnica fichaTecnica = this.buscaFindById(fichaTecnicaId);
    if (fichaTecnica != null && fichaTecnica.getStatus() == -1) {
      this.fichaTecnicaRepository.deleteById(fichaTecnicaId);
    }
  }
}
