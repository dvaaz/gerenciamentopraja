package gerenciamentorestaurante.projeto1.service;


import gerenciamentorestaurante.projeto1.entities.ProducaoDia;
import gerenciamentorestaurante.projeto1.entities.dto.request.ProducaoDiaDTORequest;
import gerenciamentorestaurante.projeto1.entities.dto.request.UpdateStatusRequest;
import gerenciamentorestaurante.projeto1.entities.dto.response.ProducaoDiaDTOResponse;
import gerenciamentorestaurante.projeto1.entities.dto.response.UpdateStatusResponse;
import gerenciamentorestaurante.projeto1.repository.ProducaoDiaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducaoDiaService {
    private final ProducaoDiaRepository producaoDiaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProducaoDiaService(ProducaoDiaRepository producaoDiaRepository) {
        this.producaoDiaRepository = producaoDiaRepository;
    }

    @Transactional
    public ProducaoDiaDTOResponse criarProducaoDia(ProducaoDiaDTORequest dtoRequest) {
        ProducaoDia proDia = modelMapper.map(dtoRequest, ProducaoDia.class);
        ProducaoDia proDiaSave = this.producaoDiaRepository.save(proDia);
        ProducaoDiaDTOResponse proDiaResponse = modelMapper.map(proDiaSave, ProducaoDiaDTOResponse.class);
        return proDiaResponse;
    }

    public ProducaoDia buscaFindById(Integer producaoDiaID) {
        return producaoDiaRepository.findById(producaoDiaID).orElse(null);
    }


    public List<ProducaoDia> listarProducaoDias(){
        return this.producaoDiaRepository.listarProducoesDia();
    }

    public ProducaoDia listarProducaoDiaPorId(Integer producaoDiaId){
        ProducaoDia producaoDia = this.producaoDiaRepository.listarProducaoDiaPorID(producaoDiaId);
        if  (producaoDia != null){
            return this.producaoDiaRepository.listarProducaoDiaPorID(producaoDiaId);
        } else return null;
    }



    @Transactional
    public UpdateStatusResponse atualizarStatusProducaoDia(Integer producaoDiaId, UpdateStatusRequest updateStatusRequest){
        ProducaoDia producaoDia = this.producaoDiaRepository.listarProducaoDiaPorID(producaoDiaId);
        if (producaoDia != null){
            producaoDia.setStatus(updateStatusRequest.getStatus());
            ProducaoDia tempResponse = producaoDiaRepository.save(producaoDia);
            return modelMapper.map(tempResponse, UpdateStatusResponse.class);
        } else return null;
    }

    @Transactional
    public void apagarLogicoProducaoDia(Integer producaoDiaId){
        this.producaoDiaRepository.apagarLogicoProducaoDia(producaoDiaId);
    }
}

