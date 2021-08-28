package br.com.jrcode.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaIdInput{
	@NotNull
	private Long id;

}
