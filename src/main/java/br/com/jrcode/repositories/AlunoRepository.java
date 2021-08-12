package br.com.jrcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{

}
