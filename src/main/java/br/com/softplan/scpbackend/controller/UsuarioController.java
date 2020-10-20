package br.com.softplan.scpbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.scpbackend.controller.dto.UsuarioDTO;
import br.com.softplan.scpbackend.controller.mapper.UsuarioMapper;
import br.com.softplan.scpbackend.entity.Usuario;
import br.com.softplan.scpbackend.enums.SwaggerConstantes;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements IUsuarioController {

	@PostMapping(value = "/autenticar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			if (!usuarioDTO.getLogin().equals("leandro") || !usuarioDTO.getSenha().equals("123456")) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(SwaggerConstantes.USUARIO_NAO_AUTENTICADO);
			}
			Usuario usuario = new Usuario();
			usuario.setId(1L);
			usuario.setLogin("leandro");
			return ResponseEntity.ok(UsuarioMapper.converterUsuario(usuario));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
