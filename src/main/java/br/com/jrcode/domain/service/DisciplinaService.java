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

import br.com.jrcode.domain.model.Disciplina;
import br.com.jrcode.domain.repository.DisciplinaRepository;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> findAll(){
		return disciplinaRepository.findAll();
	}
	
	public Disciplina findById(Long id) {
		Optional<Disciplina> obj = disciplinaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Disciplina não encontrada" + id));
	}
	
	public List<Disciplina> findByNome(String nome) {
		return disciplinaRepository.findByNomeContaining(nome);
	}
	
	public Page<Disciplina> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return disciplinaRepository.findAll(pageRequest);
	}
	
	@Transactional
	public Disciplina insert(Disciplina obj) {	
			obj.setId(null);
			disciplinaRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Disciplina update(Disciplina obj, Long id) {
		Optional<Disciplina> newObj = disciplinaRepository.findById(id);

		if (newObj.isPresent()) {

			BeanUtils.copyProperties(obj, newObj.get(), "id");
		}

		return disciplinaRepository.save(newObj.get());
	}
	
	@Transactional
	public void deleteById(Long id) {
		findById(id);
		try {
			disciplinaRepository.deleteById(id);
			disciplinaRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}

}
