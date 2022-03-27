Feature: Validar remoção dos dados da simulação

  Description: Validar a remoção de simulação para crédito do projeto "prova-tecnica-api"

  Scenario Outline: Deletar dado existente no sistema
    Given queira deletar uma pessoa da api de simulacao de credito
    When efetuar a chamada no eindpoint "<eindpoint>" pelo id "<id>"
    Then sistema retornara com o status "<statuscode>"
    Examples:
      | eindpoint           | id | statuscode |
      | /api/v1/simulacoes/ | 11 | 200        |
      | /api/v1/simulacoes/ | 12 | 200        |