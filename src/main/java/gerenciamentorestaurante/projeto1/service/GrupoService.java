package gerenciamentorestaurante.projeto1.service;

import gerenciamentorestaurante.projeto1.dto.request.GrupoDTORequest;
import gerenciamentorestaurante.projeto1.dto.response.GrupoDTOResponse;
import gerenciamentorestaurante.projeto1.entitites.Grupo;
import gerenciamentorestaurante.projeto1.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    Grupo grupoSave = this.grupoRepository.save(grupo);
    return modelMapper.map(grupoSave, GrupoDTOResponse.class);
  }

  public List<Grupo> listarGrupos() {
    List<Grupo> listaGrupo = this.grupoRepository.findAll();

    if (!listaGrupo.isEmpty()){
      return listaGrupo;
    } return null;
  }

}
