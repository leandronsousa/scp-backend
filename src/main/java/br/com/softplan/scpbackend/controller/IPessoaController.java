package br.com.softplan.scpbackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.softplan.scpbackend.controller.dto.PessoaDTO;
import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface Responsavel pelas propriedades swagger referente a API pessoa
 * 
 * @author leandro
 */
@Api(value = SwaggerConstantes.API_PESSOA)
public interface IPessoaController {

	/**
	 * Metodo da API responsavel por lista pessoas
	 * 
	 * @return
	 */
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = SwaggerConstantes.RETORNA_LISTA_PESSOAS),
	    @ApiResponse(code = 403, message = SwaggerConstantes.SEM_PERMISSAO),
	    @ApiResponse(code = 404, message = SwaggerConstantes.NAO_ENCONTRADO),
	    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
	})
	@ApiOperation(value = SwaggerConstantes.CONSULTA_LISTA_PESSOAS)
	ResponseEntity<List<PessoaDTO>> listarPessoas();
	
	/**
	 * Metodo da API responsavel por incluir uma pessoa
	 * 
	 * @param pessoaDTO
	 * @param uriBuilder
	 * @return
	 */
	@ApiResponses(value = {
		    @ApiResponse(code = 201, message = SwaggerConstantes.PESSOA_INCLUIDA_SUCESSO),
		    @ApiResponse(code = 403, message = SwaggerConstantes.SEM_PERMISSAO),
		    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
		})
	@ApiOperation(value = SwaggerConstantes.INCLUIR_PESSOA)
	ResponseEntity<?> incluirPessoa(
			@ApiParam(value = SwaggerConstantes.PESSOA_PARA_INCLUSAO) PessoaDTO pessoaDTO, 
			UriComponentsBuilder uriBuilder);

	/**
	 * Metodo da API responsavel por alterar uma pessoa
	 * 
	 * @param pessoaDTO
	 * @return
	 */
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = SwaggerConstantes.PESSOA_ALTERADA_SUCESSO),
		    @ApiResponse(code = 403, message = SwaggerConstantes.SEM_PERMISSAO),
		    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
		})
	@ApiOperation(value = SwaggerConstantes.ALTERAR_PESSOA)
	ResponseEntity<?> alterarPessoa(@ApiParam(value = SwaggerConstantes.PESSOA_PARA_ALTERACAO) PessoaDTO pessoaDTO);
	
	/**
	 * Metodo da API responsavel por excluir uma pessoa
	 * 
	 * @param id
	 * @return
	 */
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = SwaggerConstantes.PESSOA_EXCLUIDA_SUCESSO),
		    @ApiResponse(code = 403, message = SwaggerConstantes.SEM_PERMISSAO),
		    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
		})
	@ApiOperation(value = SwaggerConstantes.EXCLUIR_PESSOA)
	ResponseEntity<?> excluirPessoa(@ApiParam(value = SwaggerConstantes.ID_PESSOA) Long id);
}
