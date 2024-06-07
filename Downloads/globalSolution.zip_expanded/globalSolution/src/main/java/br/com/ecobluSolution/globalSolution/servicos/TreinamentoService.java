
package br.com.ecobluSolution.globalSolution.servicos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecobluSolution.globalSolution.controller.TreinamentoController;
import br.com.ecobluSolution.globalSolution.entity.Treinamento;
import br.com.ecobluSolution.globalSolution.repository.TreinamentoRepository;
import br.com.ecobluSolution.globalSolution.services.dto.TreinamentoDTO;

@Service
public class TreinamentoService {
    
    private final TreinamentoRepository treinamentoRepository;
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("titulo").ascending());

    @Autowired
    public TreinamentoService(TreinamentoRepository treinamentoRepository) {
        this.treinamentoRepository = treinamentoRepository;
        
    }
    

    public Page<TreinamentoDTO> buscarTreinamentos() {
        return treinamentoRepository.findAll(paginacaoPersonalizada).map(treinamento -> toDTO(treinamento, true));
    }

    public TreinamentoDTO buscarTreinamentoPorId(Long treinamentoId) {
        return treinamentoRepository.findById(treinamentoId).map(treinamento -> toDTO(treinamento, false)).orElse(null);
    }

    @Transactional
    public Treinamento salvarTreinamento(Treinamento treinamento){
    	  return treinamentoRepository.save(treinamento);
    }

    @Transactional
    public Treinamento atualizarTreinamento(Long treinamentoId, Treinamento treinamento){
        Optional<Treinamento> treinamentoOptional = treinamentoRepository.findById(treinamentoId);
        if (treinamentoOptional.isPresent()) {
            Treinamento treinamentoAtual = treinamentoOptional.get();
            treinamentoAtual.setTitulo(treinamento.getTitulo());
            treinamentoAtual.setDescricao(treinamento.getDescricao());
            treinamentoAtual.setDataInicio(treinamento.getDataInicio());
            treinamentoAtual.setDataFim(treinamento.getDataFim());
         
            return treinamentoRepository.save(treinamentoAtual);
        }
        return null;
    }

    public void deletarTreinamento(Long treinamentoId) {
        Optional<Treinamento> treinamentoOptional = treinamentoRepository.findById(treinamentoId);
        treinamentoOptional.ifPresent(treinamentoRepository::delete);
    }

    private TreinamentoDTO toDTO(Treinamento treinamento, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(TreinamentoController.class).buscarTreinamentoPorId(treinamento.getTreinamentoId())).withSelfRel();
        } else {
            link = linkTo(methodOn(TreinamentoController.class).buscarTreinamentos()).withRel("Lista de Treinamentos");
        }
        return new TreinamentoDTO(
                treinamento.getTreinamentoId(),
                treinamento.getTitulo(),
                treinamento.getDescricao(),
                treinamento.getDataInicio(),
                treinamento.getDataFim(),
                treinamento.getUsuario(),
                link
        );
    }
}

