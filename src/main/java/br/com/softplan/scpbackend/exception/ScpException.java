package br.com.softplan.scpbackend.exception;

public class ScpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ScpException() {
		super();
	}
	
	public ScpException(String msg) {
		super(msg);
	}
	
}
