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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

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
	@NotBlank(message = "Preencha o campo nome")
	@Length(min = 3, max = 30, message = "Tamanho minimo de 3 caracteres e maxímo de 30 caracteres")
	private String nome;
	@NotNull(message = "Preencha o campo escola")
	@OneToOne
	private Escola escola;
	@NotNull(message = "Preencha o campo matrícula")
	@PositiveOrZero(message = "A matricula não pode ser um valor negativo")
	private Long matricula;
	@ManyToOne
	private Turma turma;

	@OneToMany
	@JoinTable(name = "aluno_avaliacoes")
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	public Aluno(Long id,
			@NotBlank(message = "Preencha o campo nome") @Length(min = 3, max = 30, message = "Tamanho minimo de 3 caracteres e maxímo de 30 caracteres") String nome,
			@NotNull(message = "Preencha o campo escola") Escola escola,
			@NotNull(message = "Preencha o campo matrícula") @PositiveOrZero(message = "A matricula não pode ser um valor negativo") Long matricula,
			Turma turma) {
		this.id = id;
		this.nome = nome;
		this.escola = escola;
		this.matricula = matricula;
		this.turma = turma;
	}

}
