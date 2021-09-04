package br.com.jrcode.integration.turma;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.model.enums.Turno;
import br.com.jrcode.domain.service.TurmaService;

@SpringBootTest
class SalvarTurma {
	@Autowired
	private TurmaService service;


	@Test
	public void cadastroTurmaComSucesso() {
		Turma turma = new Turma(null,"Segundo H",OffsetDateTime.now(),Turno.EAD);
		turma = service.save(turma);
		
		assertThat(turma).isNotNull();
		assertThat(turma.getId()).isNotNull();
	}
	
	@Test
	public void cadastroTurmaSemNome() {
		Turma turma = new Turma(null,"Segundo H",OffsetDateTime.now(),Turno.EAD);
		turma.setNome(null);
	   
	   ConstraintViolationException erroEsperado =
	      Assertions.assertThrows(ConstraintViolationException.class, () -> {
	         service.save(turma);
	      });
	   
	   assertThat(erroEsperado).isNotNull();
	}

}
