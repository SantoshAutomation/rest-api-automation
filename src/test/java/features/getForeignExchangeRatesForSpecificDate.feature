Feature: Foreign Exchange Rate functionality for specific date

  Scenario Outline: Verify Foreign Exchange rates for Specific date
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "<Date>"
    Then It should get 200 status code

    Examples:
      | Date         |
      | 2010-01-12   |