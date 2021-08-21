package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.AlunoModel;
import br.com.jrcode.domain.model.Aluno;

@Component
public class AlunoModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public AlunoModel toModel(Aluno list) {
		return modelMapper.map(list, AlunoModel.class);
	}

	public List<AlunoModel> toCollectionModel(List<Aluno> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
