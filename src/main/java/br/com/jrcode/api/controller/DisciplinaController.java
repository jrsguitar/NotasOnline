package br.com.jrcode.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jrcode.api.assembler.DisciplinaModelAssembler;
import br.com.jrcode.api.assembler.DisciplinaModelDisassembler;
import br.com.jrcode.api.model.DisciplinaModel;
import br.com.jrcode.api.model.input.DisciplinaInput;
import br.com.jrcode.domain.model.Disciplina;
import br.com.jrcode.domain.service.DisciplinaService;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	@Autowired
	private DisciplinaModelAssembler assembler;
	@Autowired
	private DisciplinaModelDisassembler disassembler;

	@GetMapping
	public ResponseEntity<List<DisciplinaModel>> buscarTodos() {
		List<Disciplina> list = disciplinaService.findAll();
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaModel> findById(@PathVariable Long id) {
		return ResponseEntity.ok(assembler.toModel(disciplinaService.findById(id)));
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<DisciplinaModel>> findByName(@PathParam(value = "nome") String nome) {
		List<Disciplina> list = disciplinaService.findByNome(nome);
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<Disciplina>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Disciplina> list = disciplinaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<DisciplinaModel> criar(@RequestBody @Valid DisciplinaInput obj) {
		Disciplina disciplina = disassembler.toDomainObject(obj);
		DisciplinaModel disciplinaModel = assembler.toModel(disciplinaService.insert(disciplina));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disciplinaModel.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public DisciplinaModel atualizar(@PathVariable Long id, @RequestBody @Valid DisciplinaInput disciplinaInput) {
		Disciplina disciplinaAtual = disciplinaService.findById(id);
		disassembler.copyToDomainObject(disciplinaInput, disciplinaAtual);
		return assembler.toModel(disciplinaService.insert(disciplinaAtual));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		disciplinaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
