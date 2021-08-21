package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.AlunoSingleModel;
import br.com.jrcode.domain.model.Aluno;

@Component
public class AlunoSingleModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public AlunoSingleModel toModel(Aluno list) {
		return modelMapper.map(list, AlunoSingleModel.class);
	}

	public List<AlunoSingleModel> toCollectionModel(List<Aluno> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
