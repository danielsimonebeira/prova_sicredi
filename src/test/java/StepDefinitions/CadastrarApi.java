package StepDefinitions;

import DataProviders.ConfigFileReader;
import Utilities.AcoesApiComum;
import Utilities.GeraDadosTeste;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

import java.util.*;


public class CadastrarApi {
    private String url;
    private Response response;
    ConfigFileReader configFileReader;
    GeraDadosTeste geraDadosTeste = new GeraDadosTeste();
    Map<String, Object> mapValores = new HashMap<>();
    AcoesApiComum comum = new AcoesApiComum();

    @Before
    public void before() {
        configFileReader = new ConfigFileReader();
        url = configFileReader.getApplicationUrl("url.api");

    }


    @Given("cadastre uma simulacao de credito pelo eindpoint {string}")
    public void cadastre_uma_simulacao_de_credito_pelo_eindpoint(String eindPointChamada) {
        RestAssured.baseURI = url;
        RestAssured.basePath = eindPointChamada;

    }


    @When("inserir o nome {string}")
    public void inserir_o_nome(String nomePessoa) {
        mapValores.put("nome", nomePessoa);

    }

    @When("inserir os dados de {string} ja cadastrado no corpo da chamada")
    public void inserir_os_dados_de_ja_cadastrado_no_corpo_da_chamada(String nomeDuplicado) {
        mapValores.put("nome", nomeDuplicado);
        geraDadosTeste.excluiChaveEgeraDados(mapValores, false, "nome");
    }

    @When("inserir no corpo da requisicao o campo {string} com valor igual a {string}")
    public void inserirNoCorpoDaRequisicaoOCampoComValorIgualA(String nomeCampo, String nomeValor) {
        if (nomeValor.equals("null")) {
            mapValores.put(nomeCampo, null);
            geraDadosTeste.excluiChaveEgeraDados(mapValores, true, nomeCampo);

        } else {
            mapValores.put(nomeCampo, nomeValor);
            geraDadosTeste.excluiChaveEgeraDados(mapValores, true, nomeCampo);
        }
    }

    @And("o cpf {string}")
    public void o_cpf(String nuCpf) {
        mapValores.put("cpf", nuCpf);

    }

    @And("o e-mail {string}")
    public void o_e_mail(String dadoEmail) {
        mapValores.put("email", dadoEmail);

    }

    @And("o valor {string}")
    public void o_valor(String valorPreco) {
        mapValores.put("valor", valorPreco);

    }

    @And("a parcela {string}")
    public void a_parcela(String nuParcelas) {
        mapValores.put("parcelas", nuParcelas);

    }

    @And("seguro {string} na chamada")
    public void seguro_na_chamada(String seguroBollean) {
        mapValores.put("seguro", seguroBollean);

    }

    @And("o novo usuario deve ser criado com o nome {string} e o cpf {string}")
    public void oNovoUsuarioDeveSerCriadoComONomeEOCpf(String nomeValor, String cpfValor) {
        String nomeCad;
        nomeCad = response.path("nome");
        String cpfCad = response.path("cpf");

        Assert.assertEquals(nomeCad, nomeValor);
        Assert.assertEquals(cpfCad, cpfValor);

    }

    @And("a mensagem {string}")
    public void aMensagem(String msgEsperado) {
        comum.validaMensagemErroApi(response, msgEsperado);

    }

    @Then("sistema retornara com o status {string} no cadastro")
    public void sistemaRetornaraComOStatusNoCadastro(String statusCode) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        response = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(gson.toJson(mapValores))
                .when()
                .post()
                .then()
                .statusCode(Integer.parseInt(statusCode))
                .contentType(ContentType.JSON)
                .extract().response();

    }
}
