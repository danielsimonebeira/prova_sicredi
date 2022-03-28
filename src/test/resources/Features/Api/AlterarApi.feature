Feature: Validar Alteração de dados da simulação

  Description: Validar a alteraçãp de simulação para crédito do projeto "prova-tecnica-api"

  Scenario Outline: Alterar pessoa com dados validos ma simulacao
    Given queira alterar o valor "<valorCampo>" da chave "<chaveCampo>"
    When efetuar a chamada no eindpoint "<eindpoint>" pelo cpf "<campoCpf>"
    Then sistema retornara com o status "<statuscode>" na alteracao
    And o dado "<valorCampo>" da "<chaveCampo>" alterado
    Examples:
      | eindpoint           | campoCpf    | valorCampo                | chaveCampo | statuscode |
      | /api/v1/simulacoes/ | 66414919004 | Fulano Ajustado Automacao | nome       | 200        |


  Scenario Outline: Validar mensagens de erro quando alterar dado de simulacao
    Given queira alterar o valor "<valorCampo>" da chave "<chaveCampo>"
    When efetuar a chamada no eindpoint "<eindpoint>" pelo cpf "<campoCpf>"
    Then sistema retornara com o status "<statuscode>" na alteracao
    And a mensagem de erro "<mensagem>"
    Examples:
      | eindpoint           | campoCpf    | chaveCampo | valorCampo       | statuscode | mensagem                               |
      | /api/v1/simulacoes/ | 11122233344 | cpf        | Fulano Ajustado  | 404        | CPF 11122233344 não encontrado         |
      | /api/v1/simulacoes/ | 123TESTE@#$ | cpf        | Fulano Ajustado  | 404        | CPF 123TESTE@#$ não encontrado         |
      | /api/v1/simulacoes/ | 123         | cpf        | Fulano Ajustado  | 404        | CPF 123 não encontrado                 |
#     O item comentado abaixo possui possui na chamada duas mensagens e fica variando entre elas quando faz a requisicao
#      | /api/v1/simulacoes/ | 17822386034 | email      | emailIncompleto@ | 400        | não é um endereço de e-mail            |
      | /api/v1/simulacoes/ | 17822386034 | parcelas   | 0                | 400        | Parcelas deve ser igual ou maior que 2 |
