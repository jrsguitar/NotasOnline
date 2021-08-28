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

import br.com.jrcode.api.assembler.TurmaModelAssembler;
import br.com.jrcode.api.assembler.TurmaModelDisassembler;
import br.com.jrcode.api.model.TurmaModel;
import br.com.jrcode.api.model.input.TurmaInput;
import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	@Autowired
	private TurmaModelAssembler assembler;
	@Autowired
	private TurmaModelDisassembler disassembler;

	@GetMapping
	public ResponseEntity<List<TurmaModel>> buscarTodos() {
		return ResponseEntity.ok(assembler.toCollectionModel(turmaService.findAll()));
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<TurmaModel>> findByName(@PathParam(value = "nome") String nome) {
		List<Turma> list = turmaService.findByNomeContaining(nome);
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TurmaModel> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(assembler.toModel(turmaService.findById(id)));
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<Turma>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Turma> list = turmaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<TurmaModel> criar(@RequestBody @Valid TurmaInput obj) {
		Turma turma = disassembler.toDomainObject(obj);
		TurmaModel turmaModel = assembler.toModel(turmaService.insert(turma));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turmaModel.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public TurmaModel atualizar(@PathVariable Long id, @RequestBody @Valid TurmaInput turmaInput) {
		Turma turmaAtual = turmaService.findById(id);
		disassembler.copyToDomainObject(turmaInput, turmaAtual);
		return assembler.toModel(turmaService.insert(turmaAtual));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		turmaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
