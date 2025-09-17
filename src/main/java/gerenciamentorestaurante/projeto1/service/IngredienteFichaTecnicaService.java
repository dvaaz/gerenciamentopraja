package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.dto.request.IngredienteFichaTecnicaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.IngredienteFichaTecnicaDTOResponse;
import gerenciamentorestaurante.projeto1.repository.FichaTecnicaRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteFichaTecnicaRepository;
import gerenciamentorestaurante.projeto1.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IngredienteFichaTecnicaService {

  private final IngredienteFichaTecnicaRepository inFiTecRepository;
  private final IngredienteRepository ingredienteRepository;
  private final FichaTecnicaRepository fichaTecnicaRepository;
  
  @Autowired
  private IngredienteService ingredienteService;
  @Autowired
  private FichaTecnicaService fichaTecnicaService;
  
  public IngredienteFichaTecnicaService(
      IngredienteFichaTecnicaRepository inFiTecRepository,
      IngredienteRepository ingredienteRepository,
      private final FichaTecnicaRepository fichaTecnicaRepository
  ) {
    this.inFiTecRepository = inFiTecRepository;
    this.ingredienteRepository = ingredienteRepository;
    this.fichaTecnicaRepository = fichaTecnicaRepository;
  }

  @Transactional
  public IngredienteFichaTecnicaDTOResponse adicionarIngrediente(IngredienteFichaTecnicaDTORequest inFiTecDTORequest, Ingredi)
  
}

