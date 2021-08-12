package br.com.jrcode.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jrcode.domain.Escola;
import br.com.jrcode.repositories.EscolaRepository;
import br.com.jrcode.services.exception.ObjectNotFoundException;

@Service
public class EscolaService {

	@Autowired
	private EscolaRepository escolaRepository;
	
	public List<Escola> findAll(){
		return escolaRepository.findAll();
	}
	
	public Escola findById(Integer id) {
		Optional<Escola> obj = escolaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Escola não encontrada" + id));
	}
	
	public List<Escola> findByNome(String nome) {
		return escolaRepository.findByNomeContaining(nome);
	}
	
/**
	
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			escolaRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há dados relacionados ao objeto");
		}
	}**/

}
