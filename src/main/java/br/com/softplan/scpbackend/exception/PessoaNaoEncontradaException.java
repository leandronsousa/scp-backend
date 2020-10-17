package br.com.softplan.scpbackend.exception;

public class PessoaNaoEncontradaException extends ScpNegocioException {
	
	private static final long serialVersionUID = 1L;

	public PessoaNaoEncontradaException() {
		super();
	}
	
	public PessoaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
