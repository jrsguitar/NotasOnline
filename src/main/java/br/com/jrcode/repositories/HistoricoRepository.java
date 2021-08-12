package br.com.jrcode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.HistoricoNotas;

public interface HistoricoRepository extends JpaRepository<HistoricoNotas, Integer>{

}
