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

import br.com.ecobluSolution.globalSolution.controller.ParceriaController;
import br.com.ecobluSolution.globalSolution.entity.Parceria;
import br.com.ecobluSolution.globalSolution.repository.ParceriaRepository;
import br.com.ecobluSolution.globalSolution.services.dto.ParceriaDTO;

@Service
public class ParceriaService {

	 private final ParceriaRepository parceriaRepository;
	    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("nomeParceiro").ascending());

	    @Autowired
	    public ParceriaService(ParceriaRepository parceriaRepository) {
	        this.parceriaRepository = parceriaRepository;
	    }

	    public Page<ParceriaDTO> buscarParcerias() {
	        return parceriaRepository.findAll(paginacaoPersonalizada).map(parceria -> toDTO(parceria, true));
	    }

	    public ParceriaDTO buscarParceriaPorId(Long parceriaId) {
	        return parceriaRepository.findById(parceriaId).map(parceria -> toDTO(parceria, false)).orElse(null);
	    }

	    @Transactional
	    public Parceria salvarParceria(Parceria parceria){
	        return parceriaRepository.save(parceria);
	    }

	    @Transactional
	    public Parceria atualizarParceria(Long parceriaId, Parceria parceria){
	        Optional<Parceria> parceriaOptional = parceriaRepository.findById(parceriaId);
	        if (parceriaOptional.isPresent()) {
	            Parceria parceriaAtual = parceriaOptional.get();
	            parceriaAtual.setNomeParceiro(parceria.getNomeParceiro());
	            parceriaAtual.setTipoParceiro(parceria.getTipoParceiro());
	            parceriaAtual.setDescricao(parceria.getDescricao());
	            return parceriaRepository.save(parceriaAtual);
	        }
	        return null;
	    }

	    public void deletarParceria(Long parceriaId) {
	        Optional<Parceria> parceriaOptional = parceriaRepository.findById(parceriaId);
	        parceriaOptional.ifPresent(parceriaRepository::delete);
	    }

	    private ParceriaDTO toDTO(Parceria parceria, boolean self) {
	        Link link;
	        if (self) {
	            link = linkTo(methodOn(ParceriaController.class).buscarParceriaPorId(parceria.getParceriaId())).withSelfRel();
	        } else {
	            link = linkTo(methodOn(ParceriaController.class).buscarParcerias()).withRel("Lista de Parceiros");
	        }
	        return new ParceriaDTO(
	                parceria.getParceriaId(),
	                parceria.getNomeParceiro(),
	                parceria.getTipoParceiro(),
	                parceria.getDescricao(),
	                link
	        );
	    }
}
