package br.com.jrcode.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.input.ProfessorInput;
import br.com.jrcode.domain.model.Escola;
import br.com.jrcode.domain.model.Professor;

@Component
public class ProfessorModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Professor toDomainObject(ProfessorInput obj) {
		return modelMapper.map(obj, Professor.class);
	}
	
	public void copyToDomainObject(ProfessorInput objInput, Professor obj) {
		obj.setEscola(new Escola());
		modelMapper.map(objInput, obj);
	}
}
