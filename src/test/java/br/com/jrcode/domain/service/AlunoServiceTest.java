package br.com.jrcode.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.model.Avaliacao;
import br.com.jrcode.domain.repository.AlunoRepository;

class AlunoServiceTest {
	private static Long ID = 11L;
	

	@Mock
	@Autowired
	AlunoRepository alunoRepository;
	
	@Mock
	Avaliacao avaliacao;
	Aluno aluno;
	
	@InjectMocks
	@Autowired
	AlunoService alunoService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);	
		aluno = new Aluno(ID, null, null, ID, null);
	}

	@Test
	void deveAssociarAvaliacaoAoAluno() {
		when(alunoRepository.findById(ID)).thenReturn(Optional.of(aluno));
		Aluno alunoResponse = alunoService.findById(ID);
		alunoService.insertAvaliacao(alunoResponse, avaliacao);
		assertTrue(alunoResponse.getAvaliacoes().size() > 0);
		assertThat(alunoResponse.getAvaliacoes().contains(avaliacao));
	}

}
