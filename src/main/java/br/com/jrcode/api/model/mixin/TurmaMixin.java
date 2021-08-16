package br.com.jrcode.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.jrcode.domain.model.Aluno;

public abstract class TurmaMixin {
	@JsonIgnore
	private List<Aluno> alunos = new ArrayList<>();
}
