package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.jrcode.domain.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	@Query("from Professor a left join fetch a.escola where a.nome like %:nome%")
	List<Professor> findByNomeContaining(String nome);
	@Query("from Professor a left join fetch a.escola")
	List<Professor> findAll();
	
	


}
