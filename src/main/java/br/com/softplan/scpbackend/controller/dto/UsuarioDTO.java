package br.com.softplan.scpbackend.controller.dto;

import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@ApiModel(value = SwaggerConstantes.MODEL_USUARIO)
public class UsuarioDTO {

	@ApiModelProperty(value = SwaggerConstantes.ID_PESSOA, accessMode = AccessMode.READ_ONLY, dataType = "number", example = SwaggerConstantes.EXEMPLO_ID)
	private Long id;

	@ApiModelProperty(value = SwaggerConstantes.USUARIO_LOGIN, dataType = "string", example = SwaggerConstantes.EXEMPLO_LOGIN)
	private String login;
	
	@ApiModelProperty(value = SwaggerConstantes.USUARIO_SENHA, dataType = "string", example = SwaggerConstantes.EXEMPLO_SENHA)
	private String senha;
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
