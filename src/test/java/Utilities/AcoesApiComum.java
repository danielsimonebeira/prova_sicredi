package Utilities;

import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

public class AcoesApiComum {

    public void validaMensagemErroApi(@NotNull Response response, String msgEsperado) {
        String[] chaveMsg = {"nome", "cpf", "email", "valor", "parcelas", "seguro"};
        if (response.body().path("mensagem") != null) {
            String msgRecebida = response.path("mensagem");
            Assert.assertEquals(msgRecebida, msgEsperado);
        } else {
            for (String obterChave : chaveMsg) {
                String msgRecebida = response.path("erros." + obterChave);
                if (msgRecebida != null) {
                    Assert.assertEquals(msgRecebida, msgEsperado);
                }
            }
        }
    }


}
