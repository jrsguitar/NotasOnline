package br.com.jrcode.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonMixinModule extends SimpleModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		//setMixInAnnotation(Aluno.class, AlunoTurmaMixin.class);
		//setMixInAnnotation(Avaliacao.class, AvaliacaoTurmaMixin.class);
	}

}
