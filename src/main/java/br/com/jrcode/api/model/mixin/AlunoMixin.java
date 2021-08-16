package br.com.jrcode.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jrcode.domain.model.Avaliacao;

public abstract class AlunoMixin {
	@JsonIgnore
	private List<Avaliacao> avaliacoes = new ArrayList<>();
}
