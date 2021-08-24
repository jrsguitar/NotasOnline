package br.com.jrcode.domain.service;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.model.Avaliacao;
import br.com.jrcode.domain.model.Disciplina;
import br.com.jrcode.domain.model.Escola;
import br.com.jrcode.domain.model.HistoricoNotas;
import br.com.jrcode.domain.model.Professor;
import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.model.enums.Turno;
import br.com.jrcode.domain.repository.AlunoRepository;
import br.com.jrcode.domain.repository.AvaliacaoRepository;
import br.com.jrcode.domain.repository.DisciplinaRepository;
import br.com.jrcode.domain.repository.EscolaRepository;
import br.com.jrcode.domain.repository.HistoricoRepository;
import br.com.jrcode.domain.repository.ProfessorRepository;
import br.com.jrcode.domain.repository.TurmaRepository;

@Service
public class DbService {
	@Autowired
	private DisciplinaRepository rDisciplina;
	@Autowired
	private AlunoRepository rAluno;
	@Autowired
	private AvaliacaoRepository rAvaliacao;
	@Autowired
	private EscolaRepository rEscola;
	@Autowired
	private TurmaRepository rTurma;
	@Autowired
	private HistoricoRepository rHistorico;
	@Autowired
	private ProfessorRepository pRepository;

	public void instantiateTestDatabase() {
		Escola escola1 = new Escola(null, "Centro Educacional");
		Escola escola2 = new Escola(null, "Luiz Eduardo");
		Escola escola3 = new Escola(null, "Nossa Senhora");
		rEscola.saveAll(Arrays.asList(escola1, escola2, escola3));

		Turma turma1 = new Turma(null, "Terceiro A", OffsetDateTime.now(), Turno.MATUTINO);
		Turma turma2 = new Turma(null, "Terceiro B", OffsetDateTime.now(), Turno.MATUTINO);
		Turma turma3 = new Turma(null, "Quarto A", OffsetDateTime.now(), Turno.MATUTINO);
		rTurma.saveAll(Arrays.asList(turma1, turma2, turma3));

		Aluno aluno1 = new Aluno(null, "Jennifer Silva", escola1, 120325L, turma1);
		Aluno aluno2 = new Aluno(null, "Carlos Almeida", escola1, 120322L, turma1);
		Aluno aluno3 = new Aluno(null, "Ana Maria", escola1, 120352L, turma1);
		Aluno aluno4 = new Aluno(null, "Ana Lucia", escola1, 120352L, turma2);
		Aluno aluno5 = new Aluno(null, "Maria Maria", escola1, 120352L, turma2);
		Aluno aluno6 = new Aluno(null, "Carla", escola1, 120352L, turma2);
		Aluno aluno7 = new Aluno(null, "Bobo", escola1, 120352L, turma3);
		Aluno aluno8 = new Aluno(null, "Ana hghdssd", escola1, 120352L, turma3);
		Aluno aluno9 = new Aluno(null, "Lucia maria", escola1, 120352L, turma3);
		Professor prof1 = new Professor(null, "Paulo", escola1, "123456");
		pRepository.save(prof1);
		rAluno.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno3, aluno6, aluno7, aluno8, aluno9));
		rTurma.save(turma1);

		rTurma.saveAll(Arrays.asList(turma1, turma2));

		Disciplina matematica = new Disciplina(null, "Matemática", prof1, turma1);
		rDisciplina.save(matematica);
		Disciplina portugues = new Disciplina(null, "Portugues", prof1, turma1);
		rDisciplina.saveAll(Arrays.asList(matematica, portugues));

		Avaliacao av1 = new Avaliacao(null, OffsetDateTime.now(), 8.2, 5.0, aluno1, matematica);
		Avaliacao av2 = new Avaliacao(null, OffsetDateTime.now(), 7.5, 2.5, aluno1, matematica);
		Avaliacao av3 = new Avaliacao(null, OffsetDateTime.now(), 9.0, 2.5, aluno1, matematica);
		Avaliacao av4 = new Avaliacao(null, OffsetDateTime.now(), 8.2, 2.5, aluno1, portugues);
		Avaliacao av5 = new Avaliacao(null, OffsetDateTime.now(), 8.0, 2.5, aluno1, portugues);
		Avaliacao av6 = new Avaliacao(null, OffsetDateTime.now(), 5.0, 2.5, aluno1, portugues);
		Avaliacao av7 = new Avaliacao(null, OffsetDateTime.now(), 4.0, 2.5, aluno1, portugues);

		rAvaliacao.saveAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7));

		aluno1.getAvaliacoes().addAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7));
		prof1.getTurmas().add(turma1);
		pRepository.save(prof1);
		rAluno.saveAll(Arrays.asList(aluno1));
		HistoricoNotas h1 = new HistoricoNotas(null, aluno1, matematica);
		HistoricoNotas h2 = new HistoricoNotas(null, aluno1, portugues);
		rHistorico.saveAll(Arrays.asList(h1, h2));

	}

}
