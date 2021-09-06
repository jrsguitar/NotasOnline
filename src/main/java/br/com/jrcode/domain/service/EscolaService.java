package br.com.jrcode.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jrcode.domain.model.Escola;
import br.com.jrcode.domain.repository.EscolaRepository;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@Service
public class EscolaService {

	@Autowired
	private EscolaRepository escolaRepository;

	public List<Escola> findAll() {
		return escolaRepository.findAll();
	}

	public Escola findById(Long id) {
		return escolaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada id=" + id));
	}

	public List<Escola> findByNome(String nome) {
		return escolaRepository.findByNomeContaining(nome);
	}

	public Page<Escola> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		var pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return escolaRepository.findAll(pageRequest);
	}

}
