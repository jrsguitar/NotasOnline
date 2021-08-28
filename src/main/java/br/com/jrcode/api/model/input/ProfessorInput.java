package br.com.jrcode.api.model.input;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorInput{

	@NotBlank
	@Length(min = 3, max = 30)
	private String nome;

	private EscolaIdInput escola;
	@NotBlank
	private String senha;

}
