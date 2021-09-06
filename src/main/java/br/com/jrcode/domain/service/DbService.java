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
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private EscolaRepository escolaRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private HistoricoRepository historicoRepository;
	@Autowired
	private ProfessorRepository professorRepository;

	public void instantiateTestDatabase() {
		var escola1 = new Escola(1L, "Centro Educacional");
		var escola2 = new Escola(2L, "Luiz Eduardo");
		var escola3 = new Escola(3L, "Nossa Senhora");
		escolaRepository.saveAll(Arrays.asList(escola1, escola2, escola3));

		var turma1 = new Turma(1L, "Terceiro A", OffsetDateTime.now(), Turno.MATUTINO);
		var turma2 = new Turma(2L, "Terceiro B", OffsetDateTime.now(), Turno.MATUTINO);
		var turma3 = new Turma(3L, "Quarto A", OffsetDateTime.now(), Turno.MATUTINO);
		turmaRepository.saveAll(Arrays.asList(turma1, turma2, turma3));

		var aluno1 = new Aluno(1L, "Jennifer Silva", escola1, 120325L, turma1);
		var aluno2 = new Aluno(2L, "Carlos Almeida", escola1, 120322L, turma1);
		var aluno3 = new Aluno(3L, "Ana Maria", escola1, 120352L, turma1);
		var aluno4 = new Aluno(4L, "Ana Lucia", escola1, 120352L, turma2);
		var aluno5 = new Aluno(5L, "Maria Maria", escola1, 120352L, turma2);
		var aluno6 = new Aluno(6L, "Carla", escola1, 120352L, turma2);
		var aluno7 = new Aluno(7L, "Bobo", escola1, 120352L, turma3);
		var aluno8 = new Aluno(8L, "Ana hghdssd", escola1, 120352L, turma3);
		var aluno9 = new Aluno(9L, "Lucia maria", escola1, 120352L, turma3);
		var prof1 = new Professor(1L, "Paulo", escola1, "123456");
		professorRepository.save(prof1);
		alunoRepository.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5, aluno3, aluno6, aluno7, aluno8, aluno9));
		turmaRepository.save(turma1);

		turmaRepository.saveAll(Arrays.asList(turma1, turma2));

		var matematica = new Disciplina(1L, "Matemática", prof1, turma1);
		disciplinaRepository.save(matematica);
		var portugues = new Disciplina(2L, "Portugues", prof1, turma1);
		disciplinaRepository.saveAll(Arrays.asList(matematica, portugues));

		var av1 = new Avaliacao(1L, OffsetDateTime.now(), 8.2, 5.0, aluno1, matematica);
		var av2 = new Avaliacao(2L, OffsetDateTime.now(), 7.5, 2.5, aluno1, matematica);
		var av3 = new Avaliacao(3L, OffsetDateTime.now(), 9.0, 2.5, aluno1, matematica);
		var av4 = new Avaliacao(4L, OffsetDateTime.now(), 8.2, 2.5, aluno1, portugues);
		var av5 = new Avaliacao(5L, OffsetDateTime.now(), 8.0, 2.5, aluno1, portugues);
		var av6 = new Avaliacao(6L, OffsetDateTime.now(), 5.0, 2.5, aluno1, portugues);
		var av7 = new Avaliacao(7L, OffsetDateTime.now(), 4.0, 2.5, aluno1, portugues);

		avaliacaoRepository.saveAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7));

		aluno1.getAvaliacoes().addAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7));
		prof1.getTurmas().add(turma1);
		professorRepository.save(prof1);
		alunoRepository.saveAll(Arrays.asList(aluno1));
		var h1 = new HistoricoNotas(1L, aluno1, matematica);
		var h2 = new HistoricoNotas(2L, aluno1, portugues);
		historicoRepository.saveAll(Arrays.asList(h1, h2));

	}

}
