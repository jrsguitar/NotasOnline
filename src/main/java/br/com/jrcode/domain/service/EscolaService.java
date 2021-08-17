package br.com.jrcode.domain.service;

import java.util.List;
import java.util.Optional;

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
		Optional<Escola> obj = escolaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada" + id));
	}

	public List<Escola> findByNome(String nome) {
		return escolaRepository.findByNomeContaining(nome);
	}

	public Page<Escola> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return escolaRepository.findAll(pageRequest);
	}

}
