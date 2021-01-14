Feature: Foreign Exchange Rate functionality for future date

  Scenario Outline: Verify Foreign Exchange rates for Specific date
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "<Date>"
    Then It should get 200 status code
    And Verify that response should get for current date

    Examples:
      | Date         |
      | 2021-02-20   |