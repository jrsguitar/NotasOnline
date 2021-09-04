package br.com.jrcode.api.turma;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class buscarTurmas {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveRetornarStatus200_QuandoConsultar() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.basePath("/turmas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornar3Turmas_QuandoConsultar() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		
		RestAssured.given()
			.basePath("/turmas")
			.port(port)
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(3));
	}

}
