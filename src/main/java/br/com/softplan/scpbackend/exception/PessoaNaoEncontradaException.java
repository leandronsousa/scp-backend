package br.com.softplan.scpbackend.exception;

import br.com.softplan.scpbackend.enums.Mensagens;

public class PessoaNaoEncontradaException extends ScpNegocioException {
	
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException() {
		super(Mensagens.MSG_PESSOA_NAO_ENCONTRADA.getTexto());
	}
	
}
