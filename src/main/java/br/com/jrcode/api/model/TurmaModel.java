package br.com.jrcode.api.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.model.enums.Turno;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaModel{


	private Long id;
	@NotBlank(message = "Preencha o campo nome")
	private String nome;
	private OffsetDateTime ano;
	@NotNull(message = "Preencha o campo turno")
	private Turno turno;
	@JsonIgnore
	@OneToMany
	private List<Aluno> alunos = new ArrayList<>();
	

}
