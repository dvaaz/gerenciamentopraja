package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateGrupoDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.grupo.GrupoDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.shared.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.grupo.GrupoAtualizarDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.grupo.GrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.entities.dto.response.shared.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.enumerator.GrupoEnum;
import gerenciamentorestaurante.projeto1.enumerator.StatusEnum;
import gerenciamentorestaurante.projeto1.repository.FichaTecnicaRepository;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {
  private final GrupoRepository grupoRepository;
  private final IngredienteRepository ingredienteRepository;
  private final FichaTecnicaRepository fichaTecnicaRepository;
  
  private final Integer enumPadraoIngrediente = GrupoEnum.INGREDIENTE.getCodigo();
  
  private final Integer enumPadraoFichaTecnica = GrupoEnum.FICHATECNICA.getCodigo();

  @Autowired
  private ModelMapper modelMapper;


  public GrupoService (GrupoRepository grupoRepository, IngredienteRepository ingredienteRepository, FichaTecnicaRepository fichaTecnicaRepository) {
    this.grupoRepository = grupoRepository;
    this.ingredienteRepository = ingredienteRepository;
    this.fichaTecnicaRepository = fichaTecnicaRepository;
  }

  @Transactional
  public GrupoDTOResponse criarGrupo(GrupoDTORequest dtoRequest) {
    Grupo grupo = modelMapper.map(dtoRequest, Grupo.class);
    if (grupo.getTipo() == 1 || grupo.getTipo() == 2) {
      Grupo grupoSave = this.grupoRepository.save(grupo);
      return modelMapper.map(grupoSave, GrupoDTOResponse.class);
    } else{
      return null;
//      throw new IllegalArgumentException("Tipo de grupo inválido: " + grupo.getTipo() + "deve ser 1 (ingredientes) ou 2 (Fichas tecnicas)");
    }

  }

  public List<Grupo> listarTodosGrupos() {
    return this.grupoRepository.listarGrupos();
  }


  public Grupo listarGrupoPorID(Integer id) { return this.grupoRepository.buscarGrupoPorID(id); }

  public Grupo obterGrupoPadraoIngrediente() { return this.grupoRepository.buscarGrupoPadraoIngrediente(); }
  public Grupo obterGrupoPadraoFichaTecnica() { return this.grupoRepository.buscarGrupoPadraoFichaTecnica(); }

  public List<Grupo> listarGruposDeIngredientes() {return this.grupoRepository.listarGrupoDeIngredientes();}

  public List<Grupo> listarGruposDeFichaTecnicas() {return this.grupoRepository.listarGrupoDeFichaTecnicas();}

    @Transactional
    public UpdateStatusResponse atualizarStatusGrupo(Integer grupoId, UpdateStatusRequest updateStatusRequest) {
      Grupo  grupo = this.grupoRepository.buscarGrupoPorID(grupoId);
      if (grupo != null
          && (updateStatusRequest.getStatus() == StatusEnum.ATIVO.getStatus() || updateStatusRequest.getStatus() == StatusEnum.INATIVO.getStatus())) {
          grupo.setStatus(updateStatusRequest.getStatus());
          Grupo tempResponse = grupoRepository.save(grupo);
          return modelMapper.map(tempResponse, UpdateStatusResponse.class);
      } else return null;
    }

    @Transactional
    public GrupoAtualizarDTOResponse atualizarGrupo(Integer grupoId, UpdateGrupoDTORequest grupoDTORequest) {
      Grupo grupo = grupoRepository.buscarGrupoPorID(grupoId);
      if (grupo != null) {
          if (grupoDTORequest.getCor() != null) {
            grupo.setCor(grupoDTORequest.getCor());
          }
          if (grupoDTORequest.getNome()!= null) {
            grupo.setNome(grupoDTORequest.getNome());
          }
          Grupo tempResponse = grupoRepository.save(grupo);
          return modelMapper.map(tempResponse, GrupoAtualizarDTOResponse.class);
      } else return null;
    }

    public Grupo buscaFindById(Integer grupoID) {
      return this.grupoRepository.findById(grupoID).orElse(null);
    }

    /**
     * Apaga o grupo. Caso haja elementos no grupo eles serão alocados para o grupo padrao devido.
     * @param grupoId
     */
    @Transactional
    public void apagarGrupo(Integer grupoId) {
      Grupo grupo = this.grupoRepository.buscarGrupoPorID(grupoId);
      if(grupo.getTipo() == enumPadraoIngrediente){
          List<Ingrediente> listaIngredientes = this.ingredienteRepository.listarFichasIngredientesPorGrupo(grupoId);
          if (!listaIngredientes.isEmpty()){
              // busca por grupo padrao no
              Grupo grupoPadrao = this.grupoRepository.buscarGrupoPadraoIngrediente();

              for (Ingrediente ingrediente : listaIngredientes){
                  ingrediente.setGrupo(grupoPadrao);
              }
          }
      }
      else if(grupo.getTipo() == enumPadraoFichaTecnica){
          List<FichaTecnica> listaFichasTecnicas = this.fichaTecnicaRepository.listarFichasTecnicasPorGrupo(grupoId);
          if(!listaFichasTecnicas.isEmpty()) {
              Grupo grupoPadrao = this.grupoRepository.buscarGrupoPadraoFichaTecnica();

              for (FichaTecnica fichaTecnica : listaFichasTecnicas) {
                  fichaTecnica.setGrupo(grupoPadrao);
              }
          }
      }

      this.grupoRepository.apagarLogicoGrupo(grupoId);
    }

    public void destruiGrupo(Integer grupoId) {
      Grupo grupo = this.buscaFindById(grupoId);
      if (grupo == null && grupo.getStatus() == -1) {
          this.grupoRepository.delete(grupo);
      }
//        throw new IllegalArgumentException("Gupo não encontrado ou ativo");
    }
    @Transactional
  public void criarGrupoPadraoIngrediente() {
    Grupo grupoDefault = new  Grupo();
    grupoDefault.setNome("INGREDIENTE");
    grupoDefault.setStatus(StatusEnum.ATIVO.getStatus());
    grupoDefault.setCor("90EE90");
    grupoDefault.setTipo(enumPadraoIngrediente);
    grupoRepository.save(grupoDefault);
    }

    public void criarGrupoPadraoFichaTecnica() {
        Grupo grupoDefault = new  Grupo();
        grupoDefault.setNome("FICHA TECNICA");
        grupoDefault.setStatus(StatusEnum.ATIVO.getStatus());
        grupoDefault.setCor("FA8907");
        grupoDefault.setTipo(enumPadraoFichaTecnica);
        grupoRepository.save(grupoDefault);
    }


}
