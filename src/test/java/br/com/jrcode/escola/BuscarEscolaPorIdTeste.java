package br.com.jrcode.escola;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jrcode.domain.Escola;
import br.com.jrcode.services.EscolaService;

@SpringBootTest
class BuscarEscolaPorIdTeste {
	@Autowired
	private EscolaService service;

	@Test
	void findById() {
		int id = 1;
		Escola escola = service.findById(id);

		assertEquals(escola.getId(), id);

	}

	@Test
	void findByNomeContaining() {
		String nome = "on";

		List<Escola> lista = service.findByNome(nome);
		Escola escola = null;
		for (Escola e : lista) {
			if (e.getNome().toUpperCase().contains(nome.toUpperCase())) {
				escola = e;
			}
		}
		assertEquals(escola.getNome().toUpperCase().contains(nome.toUpperCase()), true);
	}

}
