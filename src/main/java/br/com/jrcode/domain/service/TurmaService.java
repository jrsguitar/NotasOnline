package br.com.jrcode.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.repository.AlunoRepository;
import br.com.jrcode.domain.repository.TurmaRepository;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	public List<Turma> findAll() {
		return turmaRepository.findAll();
	}

	public Turma findById(Long id) {
		Optional<Turma> obj = turmaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada" + id));
	}

	public List<Turma> findByNomeContaining(String nome) {
		return turmaRepository.findByNomeContaining(nome);
	}
	
	public Page<Turma> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return turmaRepository.findAll(pageRequest);
	}

	@Transactional
	public Turma insert(Turma turma) {
		Turma turmaBanco = turmaRepository.findByNome(turma.getNome());

		if (turmaBanco == null) {
			turma.setId(null);
			turmaRepository.save(turma);
		}

		return turma;
	}

	@Transactional
	public void adicionarAluno(Long idTurma, Long matriculaAluno) {
		Optional<Turma> turma = turmaRepository.findById(idTurma);
		Optional<Aluno> aluno = alunoRepository.findByMatricula(matriculaAluno);
		try {
			if (turma.isPresent() & aluno.isPresent()) {
				turma.get().getAlunos().add(aluno.get());
			}
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrada");
		}

	}

	@Transactional
	public void removerAluno(Long idTurma, Long matriculaAluno) {
		Optional<Turma> turma = turmaRepository.findById(idTurma);
		Optional<Aluno> aluno = alunoRepository.findByMatricula(matriculaAluno);
		try {
			if (turma.isPresent() & aluno.isPresent()) {
				turma.get().getAlunos().remove(aluno.get());

			}
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrada");
		}

	}

	@Transactional
	public void deleteById(Long id) {
		findById(id);
		try {
			turmaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}

	@Transactional
	public Turma update(Turma turma, Long id) {
		Optional<Turma> newTurma = turmaRepository.findById(id);

		if (newTurma.isPresent()) {

			BeanUtils.copyProperties(turma, newTurma.get(), "id");
		}

		return turmaRepository.save(newTurma.get());
	}

}
