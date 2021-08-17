package br.com.jrcode.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.input.TurmaInput;
import br.com.jrcode.domain.model.Turma;

@Component
public class TurmaModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Turma toDomainObject(TurmaInput obj) {
		return modelMapper.map(obj, Turma.class);
	}
	
	public void copyToDomainObject(TurmaInput restauranteInput, Turma obj) {		
		modelMapper.map(restauranteInput, obj);
	}
}
