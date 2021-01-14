Feature: Latest Foreign Exchange Rate functionality

  Scenario : Verify Latest Foreign Exchange rates
    Given Rates API for Latest Foreign Exchange rates "https://api.ratesapi.io/api/"
    When User perform GET operation for "latest"
    Then It should get 200 status code