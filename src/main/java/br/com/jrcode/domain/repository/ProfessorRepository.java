package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	List<Professor> findByNomeContaining(String nome);
	Professor findByNome(String nome);

}
