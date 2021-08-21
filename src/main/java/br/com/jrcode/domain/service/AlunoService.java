package br.com.jrcode.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.repository.AlunoRepository;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	public List<Aluno> findAll() {
		return alunoRepository.findAll();
	}

	public Aluno findById(Long id) {
		return alunoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Aluno não encontrado" + id));
	}

	public List<Aluno> findByNome(String nome) {
		return alunoRepository.findByNomeContaining(nome);
	}

	public Aluno findByMatricula(Long matricula) {
		return alunoRepository.findByMatricula(matricula)
				.orElseThrow(() -> new ObjectNotFoundException("Matricula não encontrada" + matricula));
	}

	public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return alunoRepository.findAll(pageRequest);
	}

}
