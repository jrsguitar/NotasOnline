package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.ProfessorModel;
import br.com.jrcode.domain.model.Professor;

@Component
public class ProfessorModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public ProfessorModel toModel(Professor list) {
		return modelMapper.map(list, ProfessorModel.class);
	}

	public List<ProfessorModel> toCollectionModel(List<Professor> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
