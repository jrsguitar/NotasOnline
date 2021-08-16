package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{
	
	List<Turma> findByNomeContaining(String nome);
	Turma findByNome(String nome);

}
