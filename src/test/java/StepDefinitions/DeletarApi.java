package StepDefinitions;

import DataProviders.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class DeletarApi {
    RequestSpecification req = RestAssured.given();
    private Response response;
    ConfigFileReader configFileReader = new ConfigFileReader();
    String url = configFileReader.getApplicationUrl("url.api");

    @Given("queira deletar uma pessoa da api de simulacao de credito")
    public void queiraDeletarUmaPessoaDaApiDeSimulacaoDeCredito() {
        RestAssured.baseURI = url;

    }

    @When("efetuar a chamada no eindpoint {string} pelo id {string}")
    public void efetuar_a_chamada_no_eindpoint_pelo_id(String eindPoint, String id) {
        response = req.log()
                .all()
                .contentType(ContentType.JSON)
                .basePath(eindPoint)
                .delete(id);

    }

    @Then("sistema retornara com o status {string}")
    public void sistema_retornara_com_o_status(String status) {
        int statusRecebido = response.getStatusCode();
        Assert.assertEquals(statusRecebido, Integer.parseInt(status));

    }
}
