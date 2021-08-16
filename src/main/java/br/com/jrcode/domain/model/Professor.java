package br.com.jrcode.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Professor extends Usuario{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senha;

	public Professor(Long id, String nome, Escola escola, String senha) {
		super(id, nome, escola);
		this.senha = senha;
	}
	@OneToMany
	@JoinTable(name = "professor_turma")
	private List<Turma> turmas = new ArrayList<>();	

}
