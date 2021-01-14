Feature: Foreign Exchange Rate functionality with Base

  Scenario Outline: Verify Latest Foreign Exchange rates with Base
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "<Date>" along with specific "<base>"
    Then It should get 200 status code

    Examples:
      | Date       | base   |
      | 2021-01-13 | USD    |
      | 2021-01-13 | GBP    |