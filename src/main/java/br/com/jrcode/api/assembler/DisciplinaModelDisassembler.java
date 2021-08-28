package br.com.jrcode.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.input.DisciplinaInput;
import br.com.jrcode.domain.model.Disciplina;
import br.com.jrcode.domain.model.Professor;
import br.com.jrcode.domain.model.Turma;

@Component
public class DisciplinaModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Disciplina toDomainObject(DisciplinaInput obj) {
		return modelMapper.map(obj, Disciplina.class);
	}
	
	public void copyToDomainObject(DisciplinaInput objInput, Disciplina obj) {
		obj.setTurma(new Turma());
		obj.setProfessor(new Professor());
		modelMapper.map(objInput, obj);
	}
}

