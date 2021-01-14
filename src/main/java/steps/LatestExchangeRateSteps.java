package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class LatestExchangeRateSteps {

        private static final Logger logger= LogManager.getLogger(LatestExchangeRateSteps.class.getName());

        private Response response;
        private RequestSpecification request;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);

        @Given("^Rates API for Latest Foreign Exchange rates \"([^\"]*)\"$")
        public void Rates_API_for_Latest_Foreign_Exchange_rates(String url){
                request = given().contentType("application/json").baseUri(url);
        }

        @When("^User perform GET operation for \"([^\"]*)\"$")
        public void user_perform_GET_operation_for(String param) {
              try {
                      response =  request.basePath(param).when().get();
                      logger.info("Getting exchange rate successfully for {"+param+"}", response.prettyPrint());
              } catch (Exception e) {
                      logger.debug("Exception in getting exchange rate for {"+param+"}", e.getMessage());
              }
        }

        @When("^User perform GET operation for \"([^\"]*)\" along with specific \"([^\"]*)\"$")
        public void userPerformGETOperationForAlongWithSpecific(String param, String symbolsOrBase) {
               try {
                       response = request.queryParam("symbols", symbolsOrBase).basePath(param).when().get();
                       logger.info("Getting exchange rate successfully for {"+param+"} with {"+symbolsOrBase+"}", response.prettyPrint());
               } catch (Exception e) {
                       logger.error("Exception in getting exchange rate with query parameters {"+symbolsOrBase+"}", e.getMessage());
               }
        }

        @When("^User perform GET operation for \"([^\"]*)\" along with specific \"([^\"]*)\" and \"([^\"]*)\"$")
        public void userPerformGETOperationForAlongWithSpecificAnd(String param, String base, String symbols) {
                try {
                        response = request.queryParam("base", base).queryParam("symbols", symbols).basePath(param).when().get();
                        logger.info("Getting exchange rate successfully for {"+param+"} with {"+base+"} and {"+symbols+"}", response.prettyPrint());
                } catch (Exception e) {
                        logger.error("Exception in getting exchange rate with query parameters {"+base+"} and {"+symbols+"}", e.getMessage());
                }
        }

        @Then("^It should get (\\d+) status code$")
        public void it_should_get_status_code(int statusCode) {
                int code = response.getStatusCode();
                Assert.assertEquals(code, statusCode);
                logger.info("Verified response status code successfully : " +code);
        }

        @And("^Verify error message \"([^\"]*)\"$")
        public void verifyErrorMessage(String errorMsg) {
                String msgError = response.getBody().jsonPath().get("error");
                Assert.assertEquals(msgError, errorMsg);
                logger.info("Verified error message successfully : " + msgError);
        }

        @And("^Verify that response should get for current date$")
        public void verifyThatResponseShouldGetForCurrentDate() {
               String[] currentDate = strDate.split(" ");
               String resDate = response.getBody().jsonPath().get("date");
               Assert.assertEquals(resDate, currentDate[0]);
               logger.debug("Verified current date in response : " + resDate);
        }
}

