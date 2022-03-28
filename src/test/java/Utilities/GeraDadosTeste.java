package Utilities;

import net.datafaker.Faker;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

public class GeraDadosTeste {

//    TO-DO: Refatorar classe para melhorar o uso dela

    public void excluiChaveEgeraDados(Map<String, Object> mapValor, Boolean usaFaker, @NotNull String campoExcluido) {
        switch (campoExcluido) {
            case "nome":
                criaDados(mapValor ,usaFaker, "cpf", "email", "valor", "parcelas", "seguro");
                break;
            case "cpf":
                criaDados(mapValor ,usaFaker, "nome", "email", "valor", "parcelas", "seguro");
                break;
            case "email":
                criaDados(mapValor ,usaFaker, "nome", "cpf", "valor", "parcelas", "seguro");
                break;
            case "valor":
                criaDados(mapValor ,usaFaker, "nome", "cpf", "email", "parcelas", "seguro");
                break;
            case "parcelas":
                criaDados(mapValor ,usaFaker, "nome", "cpf", "email", "valor", "seguro");
                break;
            case "seguro":
                criaDados(mapValor ,usaFaker, "nome", "cpf", "email", "valor", "parcelas");
                break;
            default:
                criaDados(mapValor ,usaFaker, "nome", "cpf", "email", "valor", "parcelas", "seguro");
        }

    }


    private void criaDados(Map<String, Object> mapAdicioValor, Boolean fakerAcionado, String @NotNull ... chaveCampo) {
        Faker faker = new Faker(new Locale("pt-BR"));
        for (String nomeChaveCampo : chaveCampo) {
            if (fakerAcionado.equals(true)) {
                switch (nomeChaveCampo) {
                    case "nome":
                        mapAdicioValor.put(nomeChaveCampo, faker.name().fullName());
                        break;
                    case "cpf":
                        mapAdicioValor.put(nomeChaveCampo, faker.cpf().valid(false));
                        break;
                    case "email":
                        mapAdicioValor.put(nomeChaveCampo, faker.internet().emailAddress());
                        break;
                    case "valor":
                        mapAdicioValor.put(nomeChaveCampo, faker.number().randomDouble(2,1000,40000));
                        break;
                    case "parcelas":
                        mapAdicioValor.put(nomeChaveCampo, faker.number().numberBetween(2, 48));
                        break;
                    case "seguro":
                        mapAdicioValor.put(nomeChaveCampo, faker.random().nextBoolean());
                        break;
                }
            } else {
                switch (nomeChaveCampo) {
                    case "nome":
                        mapAdicioValor.put(nomeChaveCampo, "Fulano");
                        break;
                    case "cpf":
                        mapAdicioValor.put(nomeChaveCampo, "66414919004");
                        break;
                    case "email":
                        mapAdicioValor.put(nomeChaveCampo, "fulano@gmail.com");
                        break;
                    case "valor":
                        mapAdicioValor.put(nomeChaveCampo, "11000.00");
                        break;
                    case "parcelas":
                        mapAdicioValor.put(nomeChaveCampo, 3);
                        break;
                    case "seguro":
                        mapAdicioValor.put(nomeChaveCampo, "true");
                        break;
                }
            }
        }
    }
}
