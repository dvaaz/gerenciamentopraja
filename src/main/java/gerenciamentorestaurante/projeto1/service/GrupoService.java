package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.dto.request.GrupoAtualizarDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.GrupoDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.GrupoAtualizarDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.GrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entities.Grupo;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.util.List;

@Service
public class GrupoService {
  private final GrupoRepository grupoRepository;

  @Autowired
  private ModelMapper modelMapper;


  public GrupoService (GrupoRepository grupoRepository) {
    this.grupoRepository = grupoRepository;
  }

  @Transactional
  public GrupoDTOResponse criarGrupo(GrupoDTORequest grupoDTORequest) {
    Grupo grupo = modelMapper.map(grupoDTORequest, Grupo.class);
    if (grupo.getTipo() != 1 || grupo.getTipo() != 2) {
      throw new IllegalArgumentException("Tipo de grupo inválido: " + grupo.getTipo() + "deve ser 1 (ingredientes) ou 2 (Fichas tecnicas)");

    } else{
      Grupo grupoSave = this.grupoRepository.save(grupo);
      return modelMapper.map(grupoSave, GrupoDTOResponse.class);
    }

  }

  public List<Grupo> listarGrupos() {
    return this.grupoRepository.listarGrupos();
  }

  public Grupo listarGrupoPorID(Integer id) { return this.grupoRepository.listarGrupoPorID(id); }

  public List<Grupo> listarGruposDeIngredientes() {return this.grupoRepository.buscarGrupoDeIngredientes();}

  public List<Grupo> listarGruposDeFichaTecnicas() {return this.grupoRepository.buscarGrupoDeFichaTecnicas();}

    @Transactional
    public UpdateStatusResponse atualizarStatusGrupo(Integer grupoId, UpdateStatusRequest updateStatusRequest) {
      Grupo  grupo = this.grupoRepository.listarGrupoPorID(grupoId);
      if (grupo != null
          && (updateStatusRequest.getStatus() == 1 || updateStatusRequest.getStatus() == 2)) {
          grupo.setStatus(updateStatusRequest.getStatus());
          Grupo tempResponse = grupoRepository.save(grupo);
          return modelMapper.map(tempResponse, UpdateStatusResponse.class);
      } else return null;
    }

    @Transactional
    public GrupoAtualizarDTOResponse atualizarGrupo(Integer grupoId, GrupoAtualizarDTORequest grupoDTORequest) {
      Grupo grupo = grupoRepository.listarGrupoPorID(grupoId);
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

    @Transactional
    public void apagarGrupo(Integer grupoId) {
      this.grupoRepository.apagarLogicoGrupo(grupoId);
    }

    public void destruiGrupo(Integer grupoId) {
      Grupo grupo = this.grupoRepository.listarGrupoPorID(grupoId);
      if (grupo != null && grupo.getStatus() == -1) {
          this.grupoRepository.delete(grupo);
      } else
        throw new IllegalArgumentException("Gupo não encontrado ou ativo");
    }
    @Transactional
  public void criarGrupoDefault() {
    Grupo grupoDefault = new  Grupo();
    grupoDefault.setNome("Padrão");
    grupoDefault.setStatus(1);
    grupoDefault.setCor("D3D3D3");
    grupoDefault.setTipo(0);
    grupoRepository.save(grupoDefault);
    }
}
