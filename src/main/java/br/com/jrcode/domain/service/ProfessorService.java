package br.com.jrcode.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jrcode.domain.model.Professor;
import br.com.jrcode.domain.model.Escola;
import br.com.jrcode.domain.repository.ProfessorRepository;
import br.com.jrcode.domain.repository.EscolaRepository;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private EscolaRepository escolaRepository;

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public Professor findById(Long id) {
		return professorRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado id=" + id));
	}

	public List<Professor> findByNome(String nome) {
		return professorRepository.findByNomeContaining(nome);
	}


	public Page<Professor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return professorRepository.findAll(pageRequest);
	}

	@Transactional
	public Professor insert(Professor obj) {
		Escola escola = escolaRepository.findById(obj.getEscola().getId())
				.orElseThrow(() -> new ObjectNotFoundException("Escola não encontrado id=" + obj.getEscola().getId()));
		obj.setEscola(escola);
		return professorRepository.save(obj);
	}

	@Transactional
	public void deleteById(Long id) {
		findById(id);
		try {
			professorRepository.deleteById(id);
			professorRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}

}
