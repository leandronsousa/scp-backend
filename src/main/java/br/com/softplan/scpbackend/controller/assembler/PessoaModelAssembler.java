package br.com.softplan.scpbackend.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.softplan.scpbackend.controller.PessoaController;
import br.com.softplan.scpbackend.controller.dto.PessoaDTO;

@Component
public class PessoaModelAssembler implements RepresentationModelAssembler<PessoaDTO, EntityModel<PessoaDTO>> {

	@Override
	public EntityModel<PessoaDTO> toModel(PessoaDTO pessoa) {
		return EntityModel.of(pessoa,
				linkTo(methodOn(PessoaController.class).recuperarPessoaPorId(pessoa.getId())).withSelfRel(),
				linkTo(methodOn(PessoaController.class).listarPessoas()).withRel("pessoas"));
	}

}
