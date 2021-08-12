package br.com.jrcode.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Integer matricula;

	@ManyToOne
	private Turma turma;

	@OneToMany
	@JoinTable(name = "aluno_avaliacoes")
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	public Aluno(Integer id, String nome, Escola escola, Integer matricula, Turma turma) {
		super(id, nome, escola);
		this.matricula = matricula;
		this.turma = turma;
	}
}
