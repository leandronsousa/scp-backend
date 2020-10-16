package br.com.softplan.scpbackend.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.softplan.scpbackend.enums.SwaggerConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = SwaggerConstantes.MODEL_PESSOA)
public class PessoaDTO {

	@ApiModelProperty(value = SwaggerConstantes.ID_PESSOA)
	private Long id;

	@ApiModelProperty(value = SwaggerConstantes.NOME_PESSOA)
	private String nome;

	@ApiModelProperty(value = SwaggerConstantes.SEXO_PESSOA)
	private String sexo;
	
	@ApiModelProperty(value = SwaggerConstantes.EMAIL_PESSOA)
	private String email;

	@ApiModelProperty(value = SwaggerConstantes.CPF_PESSOA)
	private String cpf;
	
	@ApiModelProperty(value = SwaggerConstantes.DATANASCIMENTO_PESSOA)
	private LocalDate dataNascimento;

	@ApiModelProperty(value = SwaggerConstantes.NATURALIDADE_PESSOA)
	private String naturalidade;
	
	@ApiModelProperty(value = SwaggerConstantes.NACIONALIDADE_PESSOA)
	private String nacionalidade;

	@ApiModelProperty(value = SwaggerConstantes.DATACADASTRO_PESSOA)
	private LocalDateTime dataCadastro;

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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
