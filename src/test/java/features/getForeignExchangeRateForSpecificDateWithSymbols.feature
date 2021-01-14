Feature: Foreign Exchange Rate functionality with Symbols

  Scenario Outline: Verify Latest Foreign Exchange rates with Symbols
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "<Date>" along with specific "<symbols>"
    Then It should get 200 status code

    Examples:
      | Date       | symbols  |
      | 2021-01-13 | USD      |
      | 2021-01-13 | USD,GBP  |