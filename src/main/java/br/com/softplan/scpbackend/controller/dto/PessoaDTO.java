package br.com.softplan.scpbackend.controller.dto;

import java.time.LocalDate;

import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@ApiModel(value = SwaggerConstantes.MODEL_PESSOA)
public class PessoaDTO {

	@ApiModelProperty(value = SwaggerConstantes.ID_PESSOA, accessMode = AccessMode.READ_ONLY, dataType = "number", example = SwaggerConstantes.EXEMPLO_ID)
	private Long id;

	@ApiModelProperty(value = SwaggerConstantes.NOME_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_NOME)
	private String nome;

	@ApiModelProperty(value = SwaggerConstantes.SEXO_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_SEXO)
	private String sexo;
	
	@ApiModelProperty(value = SwaggerConstantes.EMAIL_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_EMAIL)
	private String email;

	@ApiModelProperty(value = SwaggerConstantes.CPF_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_CPF)
	private String cpf;
	
	@ApiModelProperty(value = SwaggerConstantes.DATANASCIMENTO_PESSOA, dataType = "date", example = SwaggerConstantes.EXEMPLO_DATA_NASCIMENTO)
	private LocalDate dataNascimento;

	@ApiModelProperty(value = SwaggerConstantes.NATURALIDADE_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_NATURALIDADE)
	private String naturalidade;
	
	@ApiModelProperty(value = SwaggerConstantes.NACIONALIDADE_PESSOA, dataType = "string", example = SwaggerConstantes.EXEMPLO_NACIONALIDADE)
	private String nacionalidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

}
