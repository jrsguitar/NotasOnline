package br.com.jrcode.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.jrcode.domain.Aluno;
import br.com.jrcode.domain.Avaliacao;
import br.com.jrcode.domain.Disciplina;
import br.com.jrcode.domain.Escola;
import br.com.jrcode.domain.HistoricoNotas;
import br.com.jrcode.domain.Professor;
import br.com.jrcode.domain.Turma;
import br.com.jrcode.domain.enums.Turno;
import br.com.jrcode.repositories.AvaliacaoRepository;
import br.com.jrcode.repositories.DisciplinaRepository;
import br.com.jrcode.repositories.EscolaRepository;
import br.com.jrcode.repositories.HistoricoRepository;
import br.com.jrcode.repositories.TurmaRepository;
import br.com.jrcode.repositories.UsuarioRepository;

@Configuration
public class Config implements CommandLineRunner {
	@Autowired
	private DisciplinaRepository rDisciplina;
	@Autowired
	private UsuarioRepository rUsuario;
	@Autowired
	private AvaliacaoRepository rAvaliacao;
	@Autowired
	private EscolaRepository rEscola;
	@Autowired
	private TurmaRepository rTurma;
	@Autowired
	private HistoricoRepository rHistorico;

	@Override
	public void run(String... args) throws Exception {
		Escola escola = new Escola(null, "Centro Educacional");
		rEscola.save(escola);

		Aluno aluno1 = new Aluno(null, "Jennifer Silva", escola, 0120325, null);
		Aluno aluno2 = new Aluno(null, "Carlos Almeida", escola, 0120322, null);
		Professor prof1 = new Professor(null, "Paulo", escola, "123456");
		rUsuario.saveAll(Arrays.asList(aluno1, aluno2, prof1));

		Turma turma1 = new Turma(null, "2°-A", null, Turno.MATUTINO);
		rTurma.save(turma1);
		turma1.getAlunos().addAll(Arrays.asList(aluno1, aluno2));
		rTurma.save(turma1);

		Disciplina matematica = new Disciplina(null, "Matemática", prof1, turma1);
		rDisciplina.save(matematica);
		Disciplina portugues = new Disciplina(null, "Portugues", prof1, turma1);
		rDisciplina.saveAll(Arrays.asList(matematica, portugues));

		Avaliacao av1 = new Avaliacao(null, null, 8.2, aluno1, matematica);
		Avaliacao av2 = new Avaliacao(null, null, 7.5, aluno1, matematica);
		Avaliacao av3 = new Avaliacao(null, null, 9.0, aluno1, matematica);
		Avaliacao av4 = new Avaliacao(null, null, 8.2, aluno1, portugues);
		Avaliacao av5 = new Avaliacao(null, null, 8.0, aluno1, portugues);
		Avaliacao av6 = new Avaliacao(null, null, 5.0, aluno1, portugues);
		Avaliacao av7 = new Avaliacao(null, null, 4.0, aluno1, portugues);
		rAvaliacao.saveAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7));

		aluno1.getAvaliacoes().addAll(Arrays.asList(av1, av2, av3, av4, av5, av6,av7));
		
		HistoricoNotas h1 = new HistoricoNotas(null, aluno1, matematica);
		HistoricoNotas h2 = new HistoricoNotas(null, aluno1, portugues);
		rHistorico.saveAll(Arrays.asList(h1,h2));

	}

}
