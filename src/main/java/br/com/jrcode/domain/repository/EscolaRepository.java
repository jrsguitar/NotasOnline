package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long> {

	List<Escola> findByNomeContaining(String nome);
}
