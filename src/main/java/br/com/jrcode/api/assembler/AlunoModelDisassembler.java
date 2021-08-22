package br.com.jrcode.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.input.AlunoInput;
import br.com.jrcode.domain.model.Aluno;

@Component
public class AlunoModelDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Aluno toDomainObject(AlunoInput obj) {
		return modelMapper.map(obj, Aluno.class);
	}
	
	public void copyToDomainObject(AlunoInput restauranteInput, Aluno obj) {		
		modelMapper.map(restauranteInput, obj);
	}
}
