package br.com.softplan.scpbackend.enums;

public enum PatternsEnum {

	SOMENTE_NUMEROS("[^0-9]");
	
	private String pattern;
	
	PatternsEnum(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
	
}
