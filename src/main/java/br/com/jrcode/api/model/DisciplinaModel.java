package br.com.jrcode.api.model;

import br.com.jrcode.api.model.input.ProfessorIdInput;
import br.com.jrcode.api.model.input.TurmaIdInput;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaModel{
	private Long id;
	private String nome;	
	private ProfessorIdInput professor;
	private TurmaIdInput turma;

}
