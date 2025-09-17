package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.FichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateDescricaoRequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.ChangeToAnotherGrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.FichaTecnicaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateDescricaoResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import gerenciamentorestaurante.projeto1.repository.FichaTecnicaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    Grupo grupo = grupoRepository.buscarGrupoDeFichaTecnicaPorId(dtoRequest.getGrupoId());
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

  public FichaTecnica buscarFichaTecnicaPorId(Integer fichaTecnicaId){
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
    Grupo alteraGrupo = this.grupoRepository.buscarGrupoDeFichaTecnicaPorId(novoGrupo);
    if  (fichaTecnica != null &&  alteraGrupo != null){
      fichaTecnica.setGrupo(alteraGrupo);
      FichaTecnica tempResponse = fichaTecnicaRepository.save(fichaTecnica);

      ChangeToAnotherGrupoDTOResponse updated = new ChangeToAnotherGrupoDTOResponse();
      updated.setId(tempResponse.getId());
      updated.setGrupo(tempResponse.getGrupo().getId());

      return updated;
    } else return null;
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
