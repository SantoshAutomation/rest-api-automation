Feature: Latest Foreign Exchange Rate functionality with Base and Symbols

  Scenario Outline: Verify Latest Foreign Exchange rates with Symbols and Base
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "<Date>" along with specific "<base>" and "<symbols>"
    Then It should get 200 status code

    Examples:
      | Date   | symbols  | base |
      | latest | USD      | USD  |
      | latest | USD      | GBP  |