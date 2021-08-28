package br.com.jrcode.api.model.input;

import javax.persistence.Basic;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaInput{
	@NotBlank
	@Length(min = 3, max = 30)
	private String nome;
	@Basic
	private ProfessorIdInput professor;
	
	private TurmaIdInput turma;

}
