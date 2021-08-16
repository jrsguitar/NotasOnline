package br.com.jrcode.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jrcode.domain.model.HistoricoNotas;

public interface HistoricoRepository extends JpaRepository<HistoricoNotas, Long>{

}
