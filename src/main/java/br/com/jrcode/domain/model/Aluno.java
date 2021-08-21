package br.com.jrcode.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Aluno extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "Preencha o campo matrícula")
	@PositiveOrZero(message = "A matricula não pode ser um valor negativo")
	private Long matricula;
	@ManyToOne
	private Turma turma;

	@OneToMany
	@JoinTable(name = "aluno_avaliacoes")
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	public Aluno(Long id, String nome, Escola escola, Long matricula, Turma turma) {
		super(id, nome, escola);
		this.matricula = matricula;
		this.turma = turma;
	}
}
