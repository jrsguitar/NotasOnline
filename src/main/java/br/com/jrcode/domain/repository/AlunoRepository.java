package br.com.jrcode.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Optional<Aluno> findByMatricula(Long matricula);
	List<Aluno> findByNomeContaining(String nome);
}
