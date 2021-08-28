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

import br.com.jrcode.api.assembler.AlunoModelAssembler;
import br.com.jrcode.api.assembler.AlunoModelDisassembler;
import br.com.jrcode.api.assembler.AlunoSingleModelAssembler;
import br.com.jrcode.api.model.AlunoModel;
import br.com.jrcode.api.model.AlunoSingleModel;
import br.com.jrcode.api.model.input.AlunoInput;
import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private AlunoModelAssembler assembler;
	@Autowired
	private AlunoSingleModelAssembler singleAssembler;
	@Autowired
	private AlunoModelDisassembler disassembler;

	@GetMapping
	public ResponseEntity<List<AlunoSingleModel>> buscarTodos() {
		List<Aluno> list = alunoService.findAll();
		return ResponseEntity.ok(singleAssembler.toCollectionModel(list));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoModel> findById(@PathVariable Long id) {
		return ResponseEntity.ok(assembler.toModel(alunoService.findById(id)));
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<AlunoModel>> findByName(@PathParam(value = "nome") String nome) {
		List<Aluno> list = alunoService.findByNome(nome);
		return ResponseEntity.ok(assembler.toCollectionModel(list));
	}

	@GetMapping("/por-matricula")
	public ResponseEntity<AlunoModel> findByMatricula(@PathParam(value = "matricula") Long matricula) {
		return ResponseEntity.ok(assembler.toModel(alunoService.findByMatricula(matricula)));
	}
	@GetMapping("/por-escola/{id}")
	public ResponseEntity<List<AlunoSingleModel>> buscarPorEscola(@PathVariable Long id) {
		List<Aluno> list = alunoService.findByEscola(id);
		return ResponseEntity.ok(singleAssembler.toCollectionModel(list));
	}
	
	@GetMapping("/por-turma/{id}")
	public ResponseEntity<List<AlunoSingleModel>> buscarPorTurma(@PathVariable Long id) {
		List<Aluno> list = alunoService.findByTurma(id);
		return ResponseEntity.ok(singleAssembler.toCollectionModel(list));
	}

	@GetMapping("/paginas")
	public ResponseEntity<Page<Aluno>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Aluno> list = alunoService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<AlunoModel> criar(@RequestBody @Valid AlunoInput obj) {
		Aluno aluno = disassembler.toDomainObject(obj);
		AlunoModel alunoModel = assembler.toModel(alunoService.insert(aluno));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoModel.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public AlunoModel atualizar(@PathVariable Long id, @RequestBody @Valid AlunoInput alunoInput) {
		Aluno alunoAtual = alunoService.findById(id);		
		disassembler.copyToDomainObject(alunoInput, alunoAtual);
		return assembler.toModel(alunoService.insert(alunoAtual));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		alunoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
