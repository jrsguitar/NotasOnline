package br.com.jrcode.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jrcode.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	Optional<Aluno> findByMatricula(Long matricula);

	@Query("from Aluno a join a.escola left join fetch a.turma where a.escola.id= :id")
	List<Aluno> findByEscola(@Param("id") Long id);
	
	@Query("from Aluno a join a.escola left join fetch a.turma where a.turma.id= :id")
	List<Aluno> findByTurma(@Param("id") Long id);
	@Query("from Aluno a join a.escola left join fetch a.turma where a.nome like %:nome%")
	List<Aluno> findByNomeContaining(String nome);

	@Query("from Aluno a join a.escola left join fetch a.turma")
	List<Aluno> findAll();

}
