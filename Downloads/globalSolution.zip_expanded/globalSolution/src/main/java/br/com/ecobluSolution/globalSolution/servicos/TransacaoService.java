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

import br.com.ecobluSolution.globalSolution.controller.TransacaoController;
import br.com.ecobluSolution.globalSolution.entity.Transacao;
import br.com.ecobluSolution.globalSolution.repository.TransacaoRepository;
import br.com.ecobluSolution.globalSolution.services.dto.TransacaoDTO;

@Service
public class TransacaoService {

	private final TransacaoRepository transacaoRepository;
	private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("status").ascending());
	
	@Autowired
    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }
	
	public Page<TransacaoDTO> buscarTransacoes() {
        return transacaoRepository.findAll(paginacaoPersonalizada).map(transacao -> toDTO(transacao, true));
    }
	
	public TransacaoDTO buscarTransacaoPorId(Long transacaoId) {
        return transacaoRepository.findById(transacaoId).map(transacao -> toDTO(transacao, false)).orElse(null);
    }

	@Transactional
    public Transacao salvarTransacao(Transacao transacao){
    	  return transacaoRepository.save(transacao);
    }
    
	@Transactional
    public Transacao atualizarTransacao(Long transacaoId, Transacao transacao) {
    	Optional<Transacao> transacaoOptional = transacaoRepository.findById(transacaoId);
    	if (transacaoOptional.isPresent()) {
    		Transacao transacaoAtual = transacaoOptional.get();
    		transacaoAtual.setDataTransacao(transacao.getDataTransacao());
    		transacaoAtual.setQuantidade(transacao.getQuantidade());
    		transacaoAtual.setStatus(transacao.getStatus());
    		
    		return transacaoRepository.save(transacaoAtual);
    	}
    	return null;
    }
    
    public void deletarTransacao(Long transacaoId) {
        Optional<Transacao> transacaoOptional = transacaoRepository.findById(transacaoId);
        transacaoOptional.ifPresent(transacaoRepository::delete);
    }
    
    private TransacaoDTO toDTO(Transacao transacao, boolean self) {
    	Link link;
    	
    	if (self) {
            link = linkTo(methodOn(TransacaoController.class).buscarTransacaoPorId(transacao.getTransacaoId())).withSelfRel();
        } else {
            link = linkTo(methodOn(TransacaoController.class).buscarTransacoes()).withRel("Lista de Treinamentos");
        }
    	
    	return new TransacaoDTO(
    			transacao.getTransacaoId(),
    			transacao.getDataTransacao(),
    			transacao.getQuantidade(),
    			transacao.getStatus(),
    			transacao.getUsuario(),
    			transacao.getMaterialReciclado(),
    			
    			link
    		);
    }
    



}
