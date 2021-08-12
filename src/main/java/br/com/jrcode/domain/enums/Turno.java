package br.com.jrcode.domain.enums;
import java.util.stream.Stream;

public enum Turno {
	MATUTINO("Matutino"),
	VESPERTINO("vespertino"),
	NOTURNO("Noturno"),
	EAD("Ead");
	
	
	private String description;

	private Turno(String description) {
		this.description = description;
	}
	
	private String getDescription() {
		return description;
	}
	
	public static Turno of(String description) {
		  return Stream.of(Turno.values())
		    .filter(t -> t.getDescription().equalsIgnoreCase(description))
		    .findFirst()
		    .orElseThrow(IllegalArgumentException::new);
		}
}
