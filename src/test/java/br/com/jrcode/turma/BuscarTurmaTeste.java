package br.com.jrcode.turma;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.model.enums.Turno;
import br.com.jrcode.domain.service.TurmaService;

@SpringBootTest
class BuscarTurmaTeste {
	@Autowired
	private TurmaService service;



	@Test
	void findById() {
		Long id = 1L;
		Turma turma = service.findById(id);
		assertEquals(turma.getId(), id);

	}

	@Test
	void findByNomeContaining() {
		String nome = "Ter";

		List<Turma> lista = service.findByNomeContaining(nome);
		Turma turma = null;
		for (Turma t : lista) {
			if (t.getNome().toUpperCase().contains(nome.toUpperCase())) {
				turma = t;
			}
		}
		assertEquals(turma.getNome().toUpperCase().contains(nome.toUpperCase()), true);
	}
	
	@Test
	void insert() {
		Turma turma = new Turma(null, "Segundo B", null, Turno.MATUTINO);
		turma = service.insert(turma);
		Turma turma2 = service.findById(turma.getId());
		assertEquals(turma.getId(), turma2.getId());
		
	}

}
