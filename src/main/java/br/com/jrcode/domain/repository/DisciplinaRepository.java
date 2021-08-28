package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jrcode.domain.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	@Query("from Disciplina a left join fetch a.turma where a.nome like %:nome%")
	List<Disciplina> findByNomeContaining(String nome);
	
	@Query("from Disciplina a left join fetch a.turma")
	List<Disciplina> findAll();

		
}
