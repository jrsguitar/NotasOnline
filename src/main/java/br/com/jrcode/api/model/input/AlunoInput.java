package br.com.jrcode.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoInput {
	@NotBlank
	@Length(min = 3, max = 30)
	private String nome;
	@NotNull
	@PositiveOrZero
	private Long matricula;
	@Valid
	@NotNull
	private EscolaIdInput escola;	

}
