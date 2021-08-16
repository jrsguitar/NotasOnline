package br.com.jrcode.domain.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Administrador extends Usuario{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String senha;

	public Administrador(Long id, String nome, Escola escola, String senha) {
		super(id, nome, escola);
		this.senha = senha;
	}


}
