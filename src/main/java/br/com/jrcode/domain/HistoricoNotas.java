package br.com.jrcode.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Integer id;
	private Double media;
	@ElementCollection
	@JoinTable(name = "historico_notas_disciplinas")
	private List<Double> notas = new ArrayList<>();
	
	
	@OneToOne
	private Aluno aluno;
	
	@OneToOne
	private Disciplina disciplina;

	public HistoricoNotas(Integer id, Aluno aluno, Disciplina disciplina) {
		this.id = id;
		this.aluno = aluno;
		this.disciplina = disciplina;
		media = calcMedia(aluno.getAvaliacoes());
	}
	
	public Double calcMedia(List<Avaliacao> list) {
		double sum = 0.0;
		int contador = 0;
		
		if(!list.isEmpty()) {
			for(Avaliacao a: list) {
				if(a.getDisciplina().equals(disciplina)) {
					sum += a.getNota();
					notas.add(a.getNota());
					contador ++;
				}
			}
		}
		
		return sum / contador;
	}
	
}
