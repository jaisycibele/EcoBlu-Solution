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

import br.com.ecobluSolution.globalSolution.controller.MaterialRecicladoController;
import br.com.ecobluSolution.globalSolution.entity.MaterialReciclado;
import br.com.ecobluSolution.globalSolution.repository.MaterialRecicladoRepository;
import br.com.ecobluSolution.globalSolution.services.dto.MaterialRecicladoDTO;

@Service
public class MaterialRecicladoService {
	private final MaterialRecicladoRepository materialRecicladoRepository;
    private static final Pageable paginacaoPersonalizada = PageRequest.of(0, 5, Sort.by("tipoMaterial").ascending());

    @Autowired
    public MaterialRecicladoService(MaterialRecicladoRepository materialRecicladoRepository) {
        this.materialRecicladoRepository = materialRecicladoRepository;
    }

    public Page<MaterialRecicladoDTO> buscarMateriais() {
        return materialRecicladoRepository.findAll(paginacaoPersonalizada).map(materialReciclado -> toDTO(materialReciclado, true));
    }

    public MaterialRecicladoDTO buscarMaterialPorId(Long materialId) {
        return materialRecicladoRepository.findById(materialId).map(materialReciclado -> toDTO(materialReciclado, false)).orElse(null);
    }

    @Transactional
    public MaterialReciclado salvarMaterial(MaterialReciclado materialReciclado){
        return materialRecicladoRepository.save(materialReciclado);
    }

    @Transactional
    public MaterialReciclado atualizarMaterial(Long materialId, MaterialReciclado materialReciclado){
        Optional<MaterialReciclado> materialOptional = materialRecicladoRepository.findById(materialId);
        if (materialOptional.isPresent()) {
        	MaterialReciclado materialAtual = materialOptional.get();
        	materialAtual.setTipoMaterial(materialReciclado.getTipoMaterial());
        	materialAtual.setDescricao(materialReciclado.getDescricao());
        	materialAtual.setQuantidade(materialReciclado.getQuantidade());
        	materialAtual.setOrigem(materialReciclado.getOrigem());
        	materialAtual.setDataColeta(materialReciclado.getDataColeta());
        	
            return materialRecicladoRepository.save(materialAtual);
        }
        return null;
    }

    public void deletarMaterial(Long materialId) {
        Optional<MaterialReciclado> materialOptional = materialRecicladoRepository.findById(materialId);
        materialOptional.ifPresent(materialRecicladoRepository::delete);
    }

    private MaterialRecicladoDTO toDTO(MaterialReciclado materialReciclado, boolean self) {
        Link link;
        if (self) {
            link = linkTo(methodOn(MaterialRecicladoController.class).buscarMaterialPorId(materialReciclado.getMaterialId())).withSelfRel();
        } else {
            link = linkTo(methodOn(MaterialRecicladoController.class).buscarMateriais()).withRel("Lista de materiais");
        }
        return new MaterialRecicladoDTO(
        		materialReciclado.getMaterialId(),
        		materialReciclado.getTipoMaterial(),
        		materialReciclado.getDescricao(),
        		materialReciclado.getQuantidade(),
        		materialReciclado.getDataColeta(),
        		materialReciclado.getOrigem(),
                link
        );
    }
}
