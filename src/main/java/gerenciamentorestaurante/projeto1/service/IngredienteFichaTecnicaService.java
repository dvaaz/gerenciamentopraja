package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.FichaTecnica;
import gerenciamentorestaurante.projeto1.entities.Ingrediente;
import gerenciamentorestaurante.projeto1.entities.IngredienteFichaTecnica;
import gerenciamentorestaurante.projeto1.entities.dto.request.IngredienteFichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.IngredienteDTOResponse;
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
      private final FichaTecnicaRepository fichaTecnicaRepository
  ) {
    this.ingredienteFichaTecRepository = ingredienteFichaTecRepository;
    this.ingredienteRepository = ingredienteRepository;
    this.fichaTecnicaRepository = fichaTecnicaRepository;
  }

  @Transactional
  public IngredienteFichaTecnicaDTOResponse adicionarIngrediente(IngredienteFichaTecnicaDTORequest dtoRequestRequest){
    Ingrediente ingrediente = this.ingredienteRepository.buscarIngredientePorId(dtoRequestRequest.getIngrediente());
    if(ingrediente == null){ return null; } // personalizar returns com throw
    FichaTecnica fichaTecnica = this.fichaTecnicaRepository.buscarFichaTecnicaPorID(dtoRequestRequest.getFichaTecnica());
    if(fichaTecnica == null){return null; }
    IngredienteFichaTecnica buscarDuplicata = this.ingredienteFichaTecRepository.buscarIngredienteExisteEmFichaTecnica(ingrediente.getId(), fichaTecnica.getId());
    if(buscarDuplicata != null){ return null; }
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
  
}

