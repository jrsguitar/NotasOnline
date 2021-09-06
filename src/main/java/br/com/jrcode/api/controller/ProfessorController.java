package br.com.jrcode.api.controller;

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

import br.com.jrcode.api.assembler.ProfessorModelAssembler;
import br.com.jrcode.api.assembler.ProfessorModelDisassembler;
import br.com.jrcode.api.model.ProfessorModel;
import br.com.jrcode.api.model.input.ProfessorInput;
import br.com.jrcode.domain.model.Professor;
import br.com.jrcode.domain.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	@Autowired
	private ProfessorModelAssembler assembler;
	@Autowired
	private ProfessorModelDisassembler disassembler;

	@GetMapping
	public ResponseEntity<List<ProfessorModel>> buscarTodos() {
		List<Professor> list = professorService.findAll();
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProfessorModel> findById(@PathVariable Long id) {
		return ResponseEntity.ok(assembler.toModel(professorService.findById(id)));
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<ProfessorModel>> findByName(@PathParam(value = "nome") String nome) {
		List<Professor> list = professorService.findByNome(nome);
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}


	@GetMapping("/paginas")
	public ResponseEntity<Page<Professor>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Professor> list = professorService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<ProfessorModel> criar(@RequestBody @Valid ProfessorInput obj) {
		var professor = disassembler.toDomainObject(obj);
		var professorModel = assembler.toModel(professorService.insert(professor));
		var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professorModel.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ProfessorModel atualizar(@PathVariable Long id, @RequestBody @Valid ProfessorInput professorInput) {
		Professor professorAtual = professorService.findById(id);
		disassembler.copyToDomainObject(professorInput, professorAtual);
		return assembler.toModel(professorService.insert(professorAtual));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		professorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
