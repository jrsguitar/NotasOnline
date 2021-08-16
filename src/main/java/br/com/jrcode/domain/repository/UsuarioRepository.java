package br.com.jrcode.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
