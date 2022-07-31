package br.com.jrcode.api.model;

import br.com.jrcode.domain.model.Escola;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoModel {
	private Long id;
	private String nome;
	private Long matricula;
	private Escola escola;	
}
