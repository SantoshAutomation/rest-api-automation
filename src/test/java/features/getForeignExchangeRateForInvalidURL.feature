Feature: Foreign Exchange Rate functionality with invalid api

  Scenario: Verify Foreign Exchange rates for invalid url
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for ""
    Then It should get 400 status code
    And Verify error message "time data 'api' does not match format '%Y-%m-%d'"


