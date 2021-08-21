package br.com.jrcode.api.model;

import java.time.OffsetDateTime;

import br.com.jrcode.domain.model.enums.Turno;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaModel {

	private Long id;
	private String nome;
	private OffsetDateTime ano;
	private Turno turno;

}
