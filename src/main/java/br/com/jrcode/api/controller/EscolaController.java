package br.com.jrcode.api.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jrcode.api.assembler.EscolaModelAssembler;
import br.com.jrcode.api.model.EscolaModel;
import br.com.jrcode.domain.model.Escola;
import br.com.jrcode.domain.service.EscolaService;

@RestController
@RequestMapping("/escolas")
public class EscolaController {

	@Autowired
	private EscolaService escolaService;
	@Autowired
	private EscolaModelAssembler assembler;

	@GetMapping
	public ResponseEntity<List<EscolaModel>> buscarTodos() {
		return ResponseEntity.ok(assembler.toCollectionModel(escolaService.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EscolaModel> findById(@PathVariable Long id) {
		return ResponseEntity.ok(assembler.toModel(escolaService.findById(id)));
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<EscolaModel>> findByName(@PathParam(value = "nome") String nome) {
		return ResponseEntity.ok(assembler.toCollectionModel(escolaService.findByNome(nome)));
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<Escola>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Escola> list = escolaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}
}
