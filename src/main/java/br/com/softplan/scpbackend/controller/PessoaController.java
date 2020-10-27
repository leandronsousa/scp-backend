package br.com.softplan.scpbackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.softplan.scpbackend.controller.assembler.PessoaModelAssembler;
import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.controller.mapper.PessoaMapper;
import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import br.com.softplan.scpbackend.exception.PessoaNaoEncontradaException;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.service.IPessoaService;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController implements IPessoaController {

	private @Autowired IPessoaService service;
	
	private @Autowired PessoaModelAssembler assembler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@GetMapping
	public CollectionModel<EntityModel<PessoaDTO>> listarPessoas() {
		List<EntityModel<PessoaDTO>> pessoas = PessoaMapper.converterListaPessoa(service.listar()).stream()
				.map(assembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(pessoas, linkTo(methodOn(PessoaController.class).listarPessoas()).withSelfRel());
	}

	@GetMapping("/{id}")
	public EntityModel<PessoaDTO> recuperarPessoaPorId(@PathVariable Long id) {
		Pessoa pessoa = service.recuperarPorId(id).orElseThrow(() -> new PessoaNaoEncontradaException());
		return assembler.toModel(PessoaMapper.converterPessoa(pessoa));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<EntityModel<PessoaDTO>> incluirPessoa(@RequestBody PessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = service.incluir(PessoaMapper.converterPessoa(pessoaDTO));
			EntityModel<PessoaDTO> entityModel = assembler.toModel(PessoaMapper.converterPessoa(pessoa));
			return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
		} catch (ScpNegocioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SwaggerConstantes.ERRO_INTERNO);
		}
	}

	@PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterarPessoa(@RequestBody PessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = service.alterar(PessoaMapper.converterPessoa(pessoaDTO));
			return ResponseEntity.ok(PessoaMapper.converterPessoa(pessoa));
		} catch (PessoaNaoEncontradaException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
		} catch (ScpNegocioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SwaggerConstantes.ERRO_INTERNO);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirPessoa(@PathVariable Long id) {
		service.excluirPorId(id);
		return ResponseEntity.noContent().build();
	}

}
