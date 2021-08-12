package br.com.jrcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
