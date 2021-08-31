package br.com.jrcode.turma;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jrcode.domain.service.TurmaService;
import br.com.jrcode.domain.service.exception.DataIntegrityException;
import br.com.jrcode.domain.service.exception.ObjectNotFoundException;

@SpringBootTest
class ExcluirTurma {
	@Autowired
	private TurmaService service;

	@Test
	public void excluirTurmaEmUso() {

		DataIntegrityException erroEsperado = Assertions.assertThrows(DataIntegrityException.class,() -> {
					service.deleteById(1L);
				});
		assertThat(erroEsperado).isNotNull();
	}

	@Test
	public void excluirTurmaInexistente() {
		ObjectNotFoundException erroEsperado = Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			service.deleteById(1000L);
		});
		assertThat(erroEsperado).isNotNull();

	}

}
