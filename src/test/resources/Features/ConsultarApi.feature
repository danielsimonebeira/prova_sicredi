Feature: Validar Consulta dos dados da simulação

  Description: Validar a consulta das simulação de crédito do projeto "prova-tecnica-api"

  Scenario: Validar consulta geral de simulacoes
    Given queira visualizar todas as simulacoes
    When efetuar a chamada get no eindpoint "/api/v1/simulacoes"
    Then sistema retorna com o status "200"

  Scenario Outline: Validar consultas valídas pelo cpf na simulação
    Given queira visualizar os dados de uma pessoa especifa pelo cpf
    When efetuar a chamada do eindpoint "<eindpoint>" pelo cpf "<cpf>"
    Then sistema retorna com o status "<statuscode>"
    And os dados com o id "<id>"
    And o nome "<nome>"
    And o numero do cpf "<cpf>"
    And o endereco de email "<email>"
    And o valor R$ "<valor>"
    And o parcelamento "<parcela>"
    And o seguro "<seguro>"
    Examples:
      | eindpoint           | cpf         | statuscode | id | nome                      | email              | valor   | parcela | seguro |
      | /api/v1/simulacoes/ | 66414919004 | 200        | 11 | Fulano Ajustado Automacao | fulano@gmail.com   | 11000.0 | 3       | true   |
      | /api/v1/simulacoes/ | 66414919004 | 200        | 12 | Deltrano                  | deltrano@gmail.com | 20000.0 | 5       | false  |


  Scenario Outline: Validar mensagem de erro da consulta
    Given queira visualizar os dados de uma pessoa especifa pelo cpf
    When efetuar a chamada do eindpoint "<eindpoint>" pelo cpf "<cpf>"
    Then sistema retorna com o status "<statuscode>"
    And  a mensagem de retorna da consulta "<mensagem>"
    Examples:
      | eindpoint           | cpf         | statuscode | mensagem                       |
      | /api/v1/simulacoes/ | 5554444     | 404        | CPF 5554444 não encontrado     |
      | /api/v1/simulacoes/ | 08933597000 | 404        | CPF 08933597000 não encontrado |
      | /api/v1/restricoes/ | 97093236014 | 200        | O CPF 97093236014 tem problema |
      | /api/v1/restricoes/ | 60094146012 | 200        | O CPF 60094146012 tem problema |
      | /api/v1/restricoes/ | 84809766080 | 200        | O CPF 84809766080 tem problema |
      | /api/v1/restricoes/ | 62648716050 | 200        | O CPF 62648716050 tem problema |

