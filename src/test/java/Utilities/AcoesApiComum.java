package Utilities;

import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;

public class AcoesApiComum {
    public final String[] chave = {"nome", "cpf", "email", "valor", "parcelas", "seguro"};

    public void validaMensagemErroApi(@NotNull Response response, String msgEsperado) {
        if (response.body().path("mensagem") != null) {
            String msgRecebida = response.path("mensagem");
            Assert.assertEquals(msgRecebida, msgEsperado);
        } else {
            for (String obterChave : chave) {
                String msgRecebida = response.path("erros." + obterChave);
                if (msgRecebida != null) {
                    Assert.assertEquals(msgRecebida, msgEsperado);
                }
            }
        }
    }
}
