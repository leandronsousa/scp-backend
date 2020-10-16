package br.com.softplan.scpbackend.exception;

public class ScpNegocioException extends ScpException {

	private static final long serialVersionUID = 1L;

	public ScpNegocioException() {
		super();
	}
	
	public ScpNegocioException(String mensagem) {
		super(mensagem);
	}

}
