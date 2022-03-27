package StepDefinitions;

import Utilities.AcoesApiComum;
import Utilities.GeraDadosTeste;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AlterarApi {
    private Response response;
    GeraDadosTeste geraDadosTeste = new GeraDadosTeste();
    Map<String, Object> mapValores = new HashMap<>();
    Gson gson = new GsonBuilder().serializeNulls().create();


    @Given("queira alterar o valor {string} da chave {string}")
    public void queira_alterar_o_valor_da_chave(String valorCampo, String chaveCampo) {
        mapValores.put(chaveCampo, valorCampo);
        geraDadosTeste.excluiChaveEgeraDados(mapValores, false, chaveCampo);

    }

    @When("efetuar a chamada no eindpoint {string} pelo cpf {string}")
    public void efetuar_a_chamada_no_eindpoint_pelo_cpf(String eindPoint, String nuCpf) {
        RestAssured.basePath = eindPoint;
        response = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body(gson.toJson(mapValores))
                .when()
                .put(nuCpf)
                .then()
                .extract()
                .response();

    }

    @Then("sistema retornara com o status {string} na alteracao")
    public void sistemaRetornaraComOStatusNaAlteracao(String statusCode) {
        int statusRecebido = response.getStatusCode();
        Assert.assertEquals(statusRecebido, Integer.parseInt(statusCode));

    }

    @And("o dado {string} da {string} alterado")
    public void o_dado_da_alterado(String valorCampo, String chaveCampo) {
        String valorDadosRecebido = response.path(chaveCampo);
        Assert.assertEquals(valorDadosRecebido, valorCampo);

    }

    @And("a mensagem de erro {string}")
    public void aMensagemDeErro(String msgEsperado) {
        AcoesApiComum comum = new AcoesApiComum();
        comum.validaMensagemErroApi(response, msgEsperado);

    }
}
