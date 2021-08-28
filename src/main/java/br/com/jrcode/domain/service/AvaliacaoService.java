package br.com.jrcode.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jrcode.domain.model.Avaliacao;
import br.com.jrcode.domain.repository.AvaliacaoRepository;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	public List<Avaliacao> findAll() {
		return avaliacaoRepository.findAll();
	}

	public Avaliacao findById(Long id) {
		return avaliacaoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Avaliacao não encontrada id=" + id));
	}

	public Page<Avaliacao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return avaliacaoRepository.findAll(pageRequest);
	}

	@Transactional
	public Avaliacao insert(Avaliacao obj) {
		avaliacaoRepository.save(obj);
		return obj;
	}

	@Transactional
	public Avaliacao update(Avaliacao obj, Long id) {
		Avaliacao newObj = findById(id);
		BeanUtils.copyProperties(obj, newObj, "id");
		return avaliacaoRepository.save(newObj);
	}

	@Transactional
	public void deleteById(Long id) {
		findById(id);
		try {
			avaliacaoRepository.deleteById(id);
			avaliacaoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}

}
