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

import br.com.softplan.scpbackend.controller.assembler.PessoaModelAssembler;
import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.controller.mapper.PessoaMapper;
import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.exception.PessoaNaoEncontradaException;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController implements IPessoaController {

	private @Autowired PessoaService service;
	
	private @Autowired PessoaModelAssembler assembler;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@GetMapping
	public ResponseEntity<?> listarPessoas() {
		try {
			List<EntityModel<PessoaDTO>> pessoas = PessoaMapper.converterListaPessoa(service.listar()).stream()
					.map(assembler::toModel).collect(Collectors.toList());
			return ResponseEntity.ok(CollectionModel.of(pessoas, linkTo(methodOn(PessoaController.class).listarPessoas()).withSelfRel()));
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> recuperarPessoaPorId(@PathVariable Long id) {
		try {
    		PessoaDTO pessoa = PessoaMapper.converterPessoa(service.recuperarPorId(id));
			return ResponseEntity.ok(assembler.toModel(pessoa));
		} catch (PessoaNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluirPessoa(@RequestBody PessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = service.incluir(PessoaMapper.converterPessoa(pessoaDTO));
			EntityModel<PessoaDTO> entityModel = assembler.toModel(PessoaMapper.converterPessoa(pessoa));
			return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
		} catch (ScpNegocioException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterarPessoa(@RequestBody PessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = service.alterar(PessoaMapper.converterPessoa(pessoaDTO));
			return ResponseEntity.ok(PessoaMapper.converterPessoa(pessoa));
		} catch (PessoaNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (ScpNegocioException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirPessoa(@PathVariable Long id) {
		try {
			Pessoa pessoa = service.recuperarPorId(id);
			service.excluir(pessoa);
			return ResponseEntity.noContent().build();
		} catch (PessoaNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
