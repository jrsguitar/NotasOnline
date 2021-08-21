package br.com.jrcode.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jrcode.domain.model.enums.Turno;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Turma implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Preencha o campo nome")
	private String nome;
	private OffsetDateTime ano;
	@NotNull(message = "Preencha o campo turno")
	private Turno turno;
	@JsonIgnore
	@OneToMany
	private List<Aluno> alunos = new ArrayList<>();

	public Turma(Long id, String nome, OffsetDateTime ano, Turno turno) {
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.turno = turno;
	}

}
