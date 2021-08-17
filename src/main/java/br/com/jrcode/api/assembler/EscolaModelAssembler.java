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

	public EscolaModel toModel(Escola obj) {
		return modelMapper.map(obj, EscolaModel.class);
	}

	public List<EscolaModel> toCollectionModel(List<Escola> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
}
