Feature: Testar Cadastro de simulação de crédito

  Description: Validar o cadastro de simulação para crédito do projeto "prova-tecnica-api"

  Scenario Outline: Cadastrar pessoa com dados validos na simulacao
    Given cadastre uma simulacao de credito pelo eindpoint "<eindpoint>"
    When inserir o nome "<nome>"
    And o cpf "<cpf>"
    And o e-mail "<email>"
    And o valor "<valor>"
    And a parcela "<parcela>"
    And seguro "<seguro>" na chamada
    Then sistema retornara com o status "<statuscode>" no cadastro
    And o novo usuario deve ser criado com o nome "<nome>" e o cpf "<cpf>"
    Examples:
      | eindpoint         | nome                           | cpf         | email                     | valor | parcela | seguro | statuscode |
      | api/v1/simulacoes | João Teste Cadastra Simulacão  | 08989876010 | joTeste@testeemail.com    | 5800  | 21      | true   | 201        |
      | api/v1/simulacoes | Maria Teste Cadastra Simulacão | 58988875044 | MariaTeste@testeemail.com | 3800  | 48      | false  | 201        |

  Scenario: Validar erro no cadastro para cpf duplicado
    Given cadastre uma simulacao de credito pelo eindpoint "api/v1/simulacoes"
    When inserir os dados de "Fulano" ja cadastrado no corpo da chamada
    Then sistema retornara com o status "400" no cadastro
    And a mensagem "CPF duplicado"


  Scenario Outline: Validar mensagens de erro quando cadastra uma simulação
    Given cadastre uma simulacao de credito pelo eindpoint "<eindpoint>"
    When inserir no corpo da requisicao o campo "<nomeCampo>" com valor igual a "<valorCampo>"
    Then sistema retornara com o status "<statuscode>" no cadastro
    And a mensagem "<mensagem>"
    Examples:
      | eindpoint         | nomeCampo | valorCampo | statuscode | mensagem                                  |
      | api/v1/simulacoes | nome      | null       | 400        | Nome não pode ser vazio                   |
      | api/v1/simulacoes | cpf       | null       | 400        | CPF não pode ser vazio                    |
      | api/v1/simulacoes | email     | null       | 400        | E-mail não deve ser vazio                 |
      | api/v1/simulacoes | valor     | null       | 400        | Valor não pode ser vazio                  |
      | api/v1/simulacoes | parcelas  | null       | 400        | Parcelas não pode ser vazio               |
#      | api/v1/simulacoes | email     | joaoTeste  | 400        | não é um endereço de e-mail               | # A chamada possui duas mensagens
      | api/v1/simulacoes | valor     | 50000      | 400        | Valor deve ser menor ou igual a R$ 40.000 |
      | api/v1/simulacoes | parcelas  | 0          | 400        | Parcelas deve ser igual ou maior que 2    |