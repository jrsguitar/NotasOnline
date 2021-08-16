package br.com.jrcode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.jrcode.domain.service.DbService;

@Configuration
@Profile("dev")
public class ImportSqlConfig {
	@Autowired
	private DbService dbService;

	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}

}
