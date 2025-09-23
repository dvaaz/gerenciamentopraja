package gerenciamentorestaurante.projeto1.controller;

import gerenciamentorestaurante.projeto1.service.EstoqueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estoque")
@Tag(name="Estoque", description="Api para gerenciamento de estoque")
public class EstoqueController {
  private final EstoqueService service;

  public EstoqueController(EstoqueService estoqueService) {this.service = estoqueService;}
}
