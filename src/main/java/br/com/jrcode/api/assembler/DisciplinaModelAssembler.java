package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.DisciplinaModel;
import br.com.jrcode.domain.model.Disciplina;

@Component
public class DisciplinaModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public DisciplinaModel toModel(Disciplina list) {
		return modelMapper.map(list, DisciplinaModel.class);
	}

	public List<DisciplinaModel> toCollectionModel(List<Disciplina> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
