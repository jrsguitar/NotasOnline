package br.com.jrcode.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.jrcode.api.model.EscolaModel;
import br.com.jrcode.domain.model.Escola;

@Component
public class EscolaModelAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public EscolaModel toModel(Escola list) {
		return modelMapper.map(list, EscolaModel.class);
	}

	public List<EscolaModel> toCollectionModel(List<Escola> list) {
		return list.stream().map(obj -> toModel(obj)).collect(Collectors.toList());
	}
}
