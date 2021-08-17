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
	public Turma insert(Turma obj) {
		Turma turmaBanco = turmaRepository.findByNome(obj.getNome());

		if (turmaBanco == null) {
			obj.setId(null);
			turmaRepository.save(obj);
		}

		return obj;
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
		}catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException("Objeto não encontrada");
		}
	}

	@Transactional
	public Turma update(Turma obj, Long id) {
		Optional<Turma> newObj = turmaRepository.findById(id);

		if (newObj.isPresent()) {

			BeanUtils.copyProperties(obj, newObj.get(), "id");
		}

		return turmaRepository.save(newObj.get());
	}

}
