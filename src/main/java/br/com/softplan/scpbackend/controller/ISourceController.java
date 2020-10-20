package br.com.softplan.scpbackend.controller;

import org.springframework.http.ResponseEntity;

import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface Responsavel pela consulta do repositorio do projeto
 * 
 * @author leandro
 */
@Api(value = SwaggerConstantes.API_SOURCE)
public interface ISourceController {

	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = SwaggerConstantes.RETORNA_CODIGO_FONTE),
	    @ApiResponse(code = 404, message = SwaggerConstantes.NAO_ENCONTRADO),
	    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
	})
	@ApiOperation(value = SwaggerConstantes.CONSULTA_REPOSITORIO)
	ResponseEntity<String> retornarSourceCode();
}
