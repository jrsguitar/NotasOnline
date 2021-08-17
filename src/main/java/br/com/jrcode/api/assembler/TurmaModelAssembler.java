package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.TurmaModel;
import br.com.jrcode.domain.model.Turma;

@Component
public class TurmaModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public TurmaModel toModel(Turma list) {
		return modelMapper.map(list, TurmaModel.class);
	}

	public List<TurmaModel> toCollectionModel(List<Turma> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
