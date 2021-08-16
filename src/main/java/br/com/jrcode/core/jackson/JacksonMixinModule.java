package br.com.jrcode.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.jrcode.api.model.mixin.AlunoMixin;
import br.com.jrcode.api.model.mixin.TurmaMixin;
import br.com.jrcode.domain.model.Aluno;
import br.com.jrcode.domain.model.Turma;

@Component
public class JacksonMixinModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Turma.class, TurmaMixin.class);
		setMixInAnnotation(Aluno.class, AlunoMixin.class);
	}

}
