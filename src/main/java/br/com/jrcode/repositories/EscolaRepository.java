package br.com.jrcode.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.Escola;

public interface EscolaRepository extends JpaRepository<Escola, Integer>{
	//Método responsável por fazer uma consulta que retorna uma lista de Escolas a partir de um nome
	public List<Escola> findByNomeContaining(String nome);
}
