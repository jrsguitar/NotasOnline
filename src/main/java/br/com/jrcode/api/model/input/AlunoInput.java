package br.com.jrcode.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.jrcode.domain.model.Escola;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoInput {
	@NotBlank
	private String nome;
	@NotNull(message = "Preencha o campo matrícula")
	@PositiveOrZero(message = "A matricula não pode ser um valor negativo")
	private Long matricula;
	@NotNull
	private Escola escola;	

}
