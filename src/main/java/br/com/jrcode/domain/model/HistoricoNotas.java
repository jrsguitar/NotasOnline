package br.com.jrcode.domain.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "historico_medias_disciplinas")
public class HistoricoNotas {
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double media;
	@DecimalMax(value = "10.0", message = "O Peso não pode exceder o valor 10")
	private Double pesoAcumulado;

	@OneToOne
	private Aluno aluno;

	@OneToOne
	private Disciplina disciplina;

	public HistoricoNotas(Long id, Aluno aluno, Disciplina disciplina) {
		this.id = id;
		this.aluno = aluno;
		this.disciplina = disciplina;
		media = calcMedia(aluno.getAvaliacoes());
	}

	public Double calcMedia(List<Avaliacao> list) {
		double sum = 0.0;
		int pesoTotal = 10;
		pesoAcumulado = 0.0;

		if (!list.isEmpty()) {
			for (Avaliacao av : list) {
				if (av.getDisciplina().equals(disciplina)) {
					sum += av.getNota() * av.getPeso();
					pesoAcumulado += av.getPeso();
				}
			}
		}

		return sum / pesoTotal;
	}
}
