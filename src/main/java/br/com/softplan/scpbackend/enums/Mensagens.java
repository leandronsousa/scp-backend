package br.com.softplan.scpbackend.enums;

public enum Mensagens {

	MSG_PESSOA_CPF_NAO_PREENCHIDO("CPF não preenchido"),
	MSG_PESSOA_NOME_NAO_PREENCHIDO("Nome não preenchido"),
	MSG_PESSOA_CPF_INVALIDO("CPF inválido"), 
	MSG_PESSOA_DATANASCIMENTO_INVALIDA("Data de nascimento inválida"), 
	MSG_PESSOA_ERRO_INCLUIR("Erro ao incluir pessoa"),
	MSG_PESSOA_CPF_JA_CADASTRADO("CPF já cadastrado"),
	MSG_PESSOA_NAO_ENCONTRADA("Pessoa não encontrada"), 
	MSG_PESSOA_ERRO_ALTERAR("Erro ao alterar pessoa");
	
	private String texto;
	
	private Mensagens(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
}
