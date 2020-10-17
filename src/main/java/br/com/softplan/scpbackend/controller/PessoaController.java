package br.com.softplan.scpbackend.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.controller.mapper.PessoaMapper;
import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.exception.PessoaNaoEncontradaException;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController implements IPessoaController {

	private @Autowired PessoaService pessoaService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> listarPessoas() {
		try {
			List<PessoaDTO> lista = PessoaMapper.converterListaPessoa(pessoaService.listar());
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluirPessoa(@RequestBody PessoaDTO pessoaDTO, UriComponentsBuilder uriBuilder) {
		try {
			Pessoa pessoa = pessoaService.incluir(PessoaMapper.converterPessoa(pessoaDTO));
			URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(PessoaMapper.converterPessoa(pessoa));
		} catch (ScpNegocioException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping
	public ResponseEntity<?> alterarPessoa(@RequestBody PessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = pessoaService.alterar(PessoaMapper.converterPessoa(pessoaDTO));
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
	
	@DeleteMapping
	public ResponseEntity<?> excluirPessoa(Long id) {
		try {
			Pessoa pessoa = pessoaService.recuperarPorId(id);
			pessoaService.excluir(pessoa);
			return ResponseEntity.ok().build();
		} catch (PessoaNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
