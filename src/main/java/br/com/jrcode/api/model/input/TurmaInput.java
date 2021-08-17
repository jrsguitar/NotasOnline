package br.com.jrcode.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.jrcode.domain.model.enums.Turno;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaInput {

	@NotBlank(message = "Preencha o campo nome")
	private String nome;
	private OffsetDateTime ano;
	@NotNull(message = "Preencha o campo turno")
	private Turno turno;

}
