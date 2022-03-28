package StepDefinitions;

import DataProviders.ConfigFileReader;
import Utilities.AcoesApiComum;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class ConsultarApi {
    private RequestSpecification request;
    private Response response;
    ConfigFileReader configFileReader = new ConfigFileReader();

    @Given("queira visualizar todas as simulacoes")
    public void queira_visualizar_todas_as_simulacoes() {
        request = RestAssured.with();

    }

    @Given("queira visualizar os dados de uma pessoa especifa pelo cpf")
    public void queiraVisualizarOsDadosDeUmaPessoaEspecifaPeloCpf() {
        request = RestAssured.with();

    }

    @When("efetuar a chamada get no eindpoint {string}")
    public void efetuar_a_chamada_get_no_eindpoint(String eindPoint) {
        response = request.log()
                .all()
                .contentType(ContentType.JSON)
                .basePath(eindPoint)
                .get();
    }

    @When("efetuar a chamada do eindpoint {string} pelo cpf {string}")
    public void efetuarAChamadaDoEindpointPeloCpf(String eindPoint, String nuCpf) {
        response = request.log()
                .all()
                .contentType(ContentType.JSON)
                .basePath(eindPoint)
                .get(nuCpf);

    }

    @Then("sistema retorna com o status {string}")
    public void sistema_retorna_com_o_status(String status) {
        int statusRecebido = response.getStatusCode();
        Assert.assertEquals(statusRecebido, Integer.parseInt(status));

    }

    @And("os dados com o id {string}")
    public void osDadosComO(String id) {
        Assert.assertEquals(response.jsonPath().getString("id"), id);

    }

    @And("o nome {string}")
    public void oNome(String nmNome) {
        Assert.assertEquals(response.jsonPath().getString("nome"), nmNome);

    }

    @And("o numero do cpf {string}")
    public void oNumeroDoCpf(String nuCpf) {
        Assert.assertEquals(response.jsonPath().getString("cpf"), nuCpf);
    }

    @And("o endereco de email {string}")
    public void oEnderecoDeEmail(String nmEndereco) {
        Assert.assertEquals(response.jsonPath().getString("email"), nmEndereco);
    }

    @And("o valor R$ {string}")
    public void oValorR$(String nuValor) {
        Assert.assertEquals(response.jsonPath().getString("valor"), nuValor);
    }

    @And("o parcelamento {string}")
    public void oParcelamento(String parcelas) {
        Assert.assertEquals(response.jsonPath().getString("parcelas"), parcelas);
    }

    @And("o seguro {string}")
    public void oSeguro(String flSeguro) {
        Assert.assertEquals(response.jsonPath().getString("seguro"), flSeguro);
    }

    @And("a mensagem de retorna da consulta {string}")
    public void aMensagemDeRetornaDaConsulta(String mensagem) {
        AcoesApiComum comum = new AcoesApiComum();
        comum.validaMensagemErroApi(response, mensagem);
    }
}
