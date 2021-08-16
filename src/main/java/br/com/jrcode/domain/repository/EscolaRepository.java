package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Long>{
	//Método responsável por fazer uma consulta que retorna uma lista de Escolas a partir de um nome
	public List<Escola> findByNomeContaining(String nome);
}
