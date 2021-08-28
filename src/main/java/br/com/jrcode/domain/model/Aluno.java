package br.com.jrcode.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aluno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@OneToOne
	private Escola escola;
	private Long matricula;
	@ManyToOne
	private Turma turma;

	@OneToMany
	@JoinTable(name = "aluno_avaliacoes")
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	public Aluno(Long id, String nome, Escola escola, Long matricula, Turma turma) {
		this.id = id;
		this.nome = nome;
		this.escola = escola;
		this.matricula = matricula;
		this.turma = turma;
	}

}
