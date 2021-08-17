package br.com.jrcode.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>{
	List<Disciplina> findByNomeContaining(String nome);
}
