package br.com.jrcode.api.turma;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import util.ResourceUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class Turma {

	private static final int TURMA_ID_INEXISTENTE = 100;
	private static final int TURMA_ID_EXISTENTE = 1;
	private static final String TURMA_NOME_CORRETO = "Terceiro A";
	private static final int TURMA_QUANTIDADE = 4;
	private String jsonTurmaCorreta;

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/turmas";
		RestAssured.port = port;
		jsonTurmaCorreta = ResourceUtils.getContentFromResource("/json/correto/turma-segundo-g.json");

	}

	@Test
	public void deveRetornarStatus200_QuandoConsultar() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornar4Turmas_QuandoConsultar() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", hasSize(TURMA_QUANTIDADE));
	}

	@Test
	public void deveRetornarRespostaEStatusCorreto_QuandoConsultarTurmaExistente() {
		RestAssured.given().pathParam("turmaId", TURMA_ID_EXISTENTE).accept(ContentType.JSON).when().get("/{turmaId}")
				.then().statusCode(HttpStatus.OK.value()).body("nome", equalTo(TURMA_NOME_CORRETO));
	}

	@Test
	public void deveRetornarRespostaEStatusCorreto_QuandoConsultarTurmaInexistente() {
		RestAssured.given().pathParam("turmaId", TURMA_ID_INEXISTENTE).accept(ContentType.JSON).when().get("/{turmaId}")
				.then().statusCode(HttpStatus.NOT_FOUND.value());

	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarTurma() {
		RestAssured.given().body(jsonTurmaCorreta).contentType(ContentType.JSON).accept(ContentType.JSON).when().post()
				.then().statusCode(HttpStatus.CREATED.value());
	}

}
