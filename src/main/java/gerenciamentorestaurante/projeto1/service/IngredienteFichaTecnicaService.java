package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.IngredienteFichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica.AlterarMedidasIngredienteFichaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.ingredienteFichaTecnica.IngredienteFichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.AlterarMedidasIngredienteFichaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.IngredienteFichaTecnicaDTOResponse;
import gerenciamentorestaurante.projeto1.repository.FichaTecnicaRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteFichaTecnicaRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteFichaTecnicaService {

  private final IngredienteFichaTecnicaRepository ingredienteFichaTecRepository;
  private final IngredienteRepository ingredienteRepository;
  private final FichaTecnicaRepository fichaTecnicaRepository;
  
  @Autowired
  private IngredienteService ingredienteService;
  @Autowired
  private FichaTecnicaService fichaTecnicaService;
  
  public IngredienteFichaTecnicaService(
      IngredienteFichaTecnicaRepository ingredienteFichaTecRepository,
      IngredienteRepository ingredienteRepository,
      FichaTecnicaRepository fichaTecnicaRepository
  ) {
    this.ingredienteFichaTecRepository = ingredienteFichaTecRepository;
    this.ingredienteRepository = ingredienteRepository;
    this.fichaTecnicaRepository = fichaTecnicaRepository;
  }

  @Transactional
  public IngredienteFichaTecnicaDTOResponse criarRelacaoIngredienteFichaTecnica (IngredienteFichaTecnicaDTORequest dtoRequestRequest){
    Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(dtoRequestRequest.getIngrediente());
    if(ingrediente == null){ throw new RuntimeException("Nenhum ingrediente foi encontrado"); } // personalizar returns com throw
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(dtoRequestRequest.getFichaTecnica());
    if(fichaTecnica == null){ throw new RuntimeException("Nenhuma ficha tecnica foi encontrada"); }
    IngredienteFichaTecnica listarDuplicata = this.ingredienteFichaTecRepository.listarIngredienteExisteEmFichaTecnica(ingrediente.getId(), fichaTecnica.getId());
    if(listarDuplicata != null){ return null; }
    IngredienteFichaTecnica ingredienteFicha =  new IngredienteFichaTecnica();
    ingredienteFicha.setQtd(dtoRequestRequest.getQtd());
    ingredienteFicha.setUnidadeMedida(dtoRequestRequest.getUnidadeMedida());
    ingredienteFicha.setIngredienteId(ingrediente);
    ingredienteFicha.setFichaTecnicaId(fichaTecnica);
    ingredienteFicha.setStatus(dtoRequestRequest.getStatus());

    IngredienteFichaTecnica ingredienteFichaSave = ingredienteFichaTecRepository.save(ingredienteFicha);

    IngredienteFichaTecnicaDTOResponse dtoResponse= new IngredienteFichaTecnicaDTOResponse();
    dtoResponse.setId(ingredienteFichaSave.getId());
    dtoResponse.setQtd(ingredienteFichaSave.getQtd());
    dtoResponse.setUnidadeMedida(ingredienteFichaSave.getUnidadeMedida());
    dtoResponse.setIngrediente(ingredienteFichaSave.getIngredienteId().getId());
    dtoResponse.setFichaTecnica(ingredienteFichaSave.getFichaTecnicaId().getId());
    dtoResponse.setStatus(ingredienteFichaSave.getStatus());
    return dtoResponse;

  }

  public AlterarMedidasIngredienteFichaDTOResponse alterarMedidasIngredienteFicha(Integer ingredienteFichaId, AlterarMedidasIngredienteFichaDTORequest dtoRequest) {
    IngredienteFichaTecnica ingredienteFichaTecnica = this.ingredienteFichaTecRepository.buscarIngredienteFichaTecnicaPorID(ingredienteFichaId);
    if (ingredienteFichaTecnica != null) {
      ingredienteFichaTecnica.setUnidadeMedida(dtoRequest.getUnidadeMedida());
      ingredienteFichaTecnica.setQtd(dtoRequest.getQtd());

      IngredienteFichaTecnica save = ingredienteFichaTecRepository.save(ingredienteFichaTecnica);

      AlterarMedidasIngredienteFichaDTOResponse dtoResponse= new AlterarMedidasIngredienteFichaDTOResponse();
      dtoResponse.setId(save.getId());
      dtoResponse.setQtd(save.getQtd());
      dtoResponse.setUnidadeMedida(save.getUnidadeMedida());

      return dtoResponse;

    } throw new RuntimeException("Nenhuma ficha tecnica com esse ID foi encontrada");
  }

  public void pagarLogicoIngredienteFichaTecnica(Integer ingredienteFichaId){
    this.ingredienteFichaTecRepository.apagarLogicoIngredienteFichaTecnica(ingredienteFichaId);
  }
}

