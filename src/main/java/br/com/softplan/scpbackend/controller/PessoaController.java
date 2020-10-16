package br.com.softplan.scpbackend.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.controller.mapper.PessoaMapper;
import br.com.softplan.scpbackend.entity.Pessoa;
import br.com.softplan.scpbackend.exception.ScpNegocioException;
import br.com.softplan.scpbackend.service.PessoaService;

@RestController
@RequestMapping(value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController implements IPessoaController {

	private @Autowired PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> listarPessoas() {
		try {
			List<PessoaDTO> lista = PessoaMapper.converterListaPessoa(pessoaService.listar());
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity incluirPessoa(@RequestBody PessoaDTO pessoaDTO, UriComponentsBuilder uriBuilder) {
		try {
			Pessoa pessoa = pessoaService.incluir(PessoaMapper.converterPessoa(pessoaDTO));
			URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(PessoaMapper.converterPessoa(pessoa));
		} catch (ScpNegocioException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
