package br.com.jrcode.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.jrcode.domain.enums.Turno;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Turma implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private LocalDate ano;
	private Turno turno;

	@OneToMany
	private List<Aluno> alunos = new ArrayList<>();

	public Turma(Integer id, String nome, LocalDate ano, Turno turno) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.turno = turno;
	}
	
	

}
