package br.com.jrcode.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.repository.AlunoRepository;
import br.com.jrcode.domain.repository.EscolaRepository;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EscolaRepository escolaRepository;

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno findById(Long id) {
		return alunoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado id=" + id));
	}

	public List<Aluno> findByNome(String nome) {
		return alunoRepository.findByNomeContaining(nome);
	}

	public List<Aluno> findByEscola(Long id) {
		return alunoRepository.findByEscola(id);
	}

	public List<Aluno> findByTurma(Long id) {
		return alunoRepository.findByTurma(id);
	}

	public Aluno findByMatricula(Long matricula) {
		return alunoRepository.findByMatricula(matricula)
				.orElseThrow(() -> new ObjectNotFoundException("Matricula não encontrada " + matricula));
	}

	public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		var pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return alunoRepository.findAll(pageRequest);
	}

	@Transactional
	public Aluno insert(Aluno obj) {
		var escola = escolaRepository.findById(obj.getEscola().getId())
				.orElseThrow(() -> new ObjectNotFoundException("Escola não encontrado id=" + obj.getEscola().getId()));
		obj.setEscola(escola);
		return alunoRepository.save(obj);
	}

	@Transactional
	public void deleteById(Long id) {
		findById(id);
		try {
			alunoRepository.deleteById(id);
			alunoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}

}
