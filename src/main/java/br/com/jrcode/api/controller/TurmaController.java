package br.com.jrcode.api.controller;

import java.net.URI;
import java.util.List;

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

import br.com.jrcode.domain.model.Turma;
import br.com.jrcode.domain.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> buscarTodos() {
		List<Turma> list = turmaService.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(turmaService.findById(id));
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<Turma>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Turma> list = turmaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PutMapping("/adicionar-aluno")
	public ResponseEntity<Void> adicionarAluno(@PathParam(value = "idTurma") Long idTurma,
			@PathParam(value = "matriculaAluno") Long matriculaAluno) {

		turmaService.adicionarAluno(idTurma, matriculaAluno);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/remover-aluno")
	public ResponseEntity<Void> removerAluno(@PathParam(value = "idTurma") Long idTurma,
			@PathParam(value = "matriculaAluno") Long matriculaAluno) {

		turmaService.removerAluno(idTurma, matriculaAluno);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping 
	public ResponseEntity<Turma> criar(@RequestBody Turma obj){ 
		   obj = turmaService.insert(obj); 
		   URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); 
		   return ResponseEntity.created(uri).build(); 
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> remover(@PathVariable Long id){ 
		turmaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
