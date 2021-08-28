package br.com.jrcode.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoIdInput{
	@NotNull
	private Long id;

}
