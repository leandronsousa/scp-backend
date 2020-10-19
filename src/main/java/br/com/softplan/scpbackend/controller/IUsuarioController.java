package br.com.softplan.scpbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.softplan.scpbackend.controller.dto.UsuarioDTO;
import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface Responsavel pelas propriedades swagger referente a API usuario
 * 
 * @author leandro
 */
@Api(value = SwaggerConstantes.API_USUARIO)
public interface IUsuarioController {

	/**
	 * Metodo da API responsavel por autenticar o usuario
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = SwaggerConstantes.USUARIO_AUTENTICADO_SUCESSO),
	    @ApiResponse(code = 401, message = SwaggerConstantes.USUARIO_NAO_AUTENTICADO),
	    @ApiResponse(code = 500, message = SwaggerConstantes.ERRO_INTERNO),
	})
	@ApiOperation(value = SwaggerConstantes.AUTENTICAR_USUARIO)
	ResponseEntity<?> autenticar(@RequestBody UsuarioDTO usuarioDTO);
	
}
